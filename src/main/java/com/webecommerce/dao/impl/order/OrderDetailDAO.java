package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailDAO extends AbstractDAO<OrderDetailEntity> implements IOrderDetailDAO {

    public OrderDetailDAO() {
        super(OrderDetailEntity.class);
    }


    @Override
    public List<OrderDetailEntity> findAllByOrderId(Long orderId) {
        String query = "SELECT od FROM OrderDetailEntity od WHERE od.order.id = :orderId";
        return entityManager.createQuery(query, OrderDetailEntity.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    @Override
    public List<DisplayOrderDetailDTO> showOrderDetail(Long orderId, EnumOrderStatus status) {
        String jpql = "SELECT " +
                "od.id, " +
                "od.quantity, " +
                "pv.imageUrl, " +
                "pv.color, " +
                "pv.size, " +
                "p.name, " +
                "CASE " +
                "  WHEN pd.discountPercentage IS NOT NULL THEN (pv.price * (1.0 - (pd.discountPercentage / 100.0))) " +
                "  ELSE pv.price " +
                "END " +
                "FROM OrderDetailEntity od " +
                "LEFT JOIN od.productVariant pv " +
                "LEFT JOIN pv.product p " +
                "LEFT JOIN od.productDiscount pd " +
                "INNER JOIN od.order o " +
                "WHERE o.id = :orderId";
//
//        if (status.equals(EnumOrderStatus.PROCESSED) || status.equals(EnumOrderStatus.RECEIVED)) {
//            jpql += " AND EXISTS (SELECT 1 FROM ReturnOrderEntity ro WHERE ro.orderDetail.id = od.id AND ro.status = 2)";
//        }

        List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                .setParameter("orderId", orderId)
                .getResultList();

        Map<Long, Long> quantityReturnMap = new HashMap<>();

        if(status.equals(EnumOrderStatus.CANCELLED)) {
            String query = "SELECT ro.orderDetail.id, ro.quantityReturn FROM ReturnOrderEntity ro WHERE ro.orderDetail.order.id = :orderId";
            List<Object[]> rawResultsQuantity = entityManager.createQuery(query, Object[].class)
                    .setParameter("orderId", orderId)
                    .getResultList();

            for (Object[] result : rawResultsQuantity) {
                Long orderDetailId = (Long) result[0];
                Long quantityReturnValue = (Long) result[1];
                quantityReturnMap.put(orderDetailId, quantityReturnValue);
            }
        }

        List<DisplayOrderDetailDTO> resultList = new ArrayList<>();

        for (Object[] result : rawResults) {
            Long orderDetailId = (Long) result[0];
            Integer quantityInteger = (Integer) result[1];
            Long quantity = Long.valueOf(quantityInteger);
            String imgUrl = (String) result[2];
            String color = (String) result[3];
            String size = (String) result[4];
            String productName = (String) result[5];
            Double total = (Double) result[6];

            if(quantity == 0 && status.equals(EnumOrderStatus.CANCELLED)) {
                quantity = quantityReturnMap.get(orderDetailId);
            }
            else if(quantity == 0) {
                continue;
            }


            resultList.add(new DisplayOrderDetailDTO(orderDetailId, quantity ,imgUrl, color, size, productName, total));
        }

        return resultList;
    }


    public List<DisplayOrderDetailDTO> showOrderDetailReview(Long orderId, EnumOrderStatus status, EnumProductStatus enumProductStatus) {
        String jpql = "SELECT " +
                "od.id, " +
                "od.quantity, " +
                "pv.imageUrl, " +
                "pv.color, " +
                "pv.size, " +
                "p.name, " +
                "CASE " +
                "  WHEN pd.discountPercentage IS NOT NULL THEN (pv.price * (1.0 - (pd.discountPercentage / 100.0))) " +
                "  ELSE pv.price " +
                "END " +
                "FROM OrderDetailEntity od " +
                "LEFT JOIN od.productVariant pv " +
                "LEFT JOIN pv.product p " +
                "LEFT JOIN od.productDiscount pd " +
                "INNER JOIN od.order o " +
                "WHERE o.id = :orderId and od.productVariant.product.status = :productStatus";
//
//        if (status.equals(EnumOrderStatus.PROCESSED) || status.equals(EnumOrderStatus.RECEIVED)) {
//            jpql += " AND EXISTS (SELECT 1 FROM ReturnOrderEntity ro WHERE ro.orderDetail.id = od.id AND ro.status = 2)";
//        }

        List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                .setParameter("orderId", orderId)
                .setParameter("productStatus", enumProductStatus)
                .getResultList();

        Map<Long, Long> quantityReturnMap = new HashMap<>();

        if(status.equals(EnumOrderStatus.CANCELLED)) {
            String query = "SELECT ro.orderDetail.id, ro.quantityReturn FROM ReturnOrderEntity ro WHERE ro.orderDetail.order.id = :orderId";
            List<Object[]> rawResultsQuantity = entityManager.createQuery(query, Object[].class)
                    .setParameter("orderId", orderId)
                    .getResultList();

            for (Object[] result : rawResultsQuantity) {
                Long orderDetailId = (Long) result[0];
                Long quantityReturnValue = (Long) result[1];
                quantityReturnMap.put(orderDetailId, quantityReturnValue);
            }
        }

        List<DisplayOrderDetailDTO> resultList = new ArrayList<>();

        for (Object[] result : rawResults) {
            Long orderDetailId = (Long) result[0];
            Integer quantityInteger = (Integer) result[1];
            Long quantity = Long.valueOf(quantityInteger);
            String imgUrl = (String) result[2];
            String color = (String) result[3];
            String size = (String) result[4];
            String productName = (String) result[5];
            Double total = (Double) result[6];

            if(quantity == 0 && status.equals(EnumOrderStatus.CANCELLED)) {
                quantity = quantityReturnMap.get(orderDetailId);
            }
            else if(quantity == 0) {
                continue;
            }


            resultList.add(new DisplayOrderDetailDTO(orderDetailId, quantity ,imgUrl, color, size, productName, total));
        }

        return resultList;
    }

    @Override
    public EnumOrderStatus getCurrentStatus(Long orderId) {
        String jpql = "SELECT os.status " +
                "FROM OrderStatusEntity os " +
                "WHERE os.order.id = :orderId " +
                "AND os.date = (SELECT MAX(os2.date) " +
                "FROM OrderStatusEntity os2 " +
                "WHERE os2.order.id = :orderId)";

        return entityManager.createQuery(jpql, EnumOrderStatus.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
    }

    @Override
    public DisplayOrderDetailDTO findOrderDetail(Long orderDetailId) {
        String jpql = "SELECT " +
                "od.id, " +
                "od.quantity, " +
                "pv.imageUrl, " +
                "pv.color, " +
                "pv.size, " +
                "p.name, " +
                "CASE " +
                "  WHEN pd.discountPercentage IS NOT NULL THEN (pv.price * (1.0 - (pd.discountPercentage / 100.0))) " +
                "  ELSE pv.price " +
                "END " +
                "FROM OrderDetailEntity od " +
                "LEFT JOIN od.productVariant pv " +
                "LEFT JOIN pv.product p " +
                "LEFT JOIN od.productDiscount pd " +
                "INNER JOIN od.order o " +
                "WHERE od.id = :orderDetailId";


        Object[] rawResults = entityManager.createQuery(jpql, Object[].class)
                .setParameter("orderDetailId", orderDetailId)
                .getSingleResult();

        Long orderDetailIdOld = (Long) rawResults[0];

        Integer quantityInteger = (Integer) rawResults[1];
        Long quantity = quantityInteger.longValue();

        String imgUrl = (String) rawResults[2];
        String color = (String) rawResults[3];
        String size = (String) rawResults[4];
        String productName = (String) rawResults[5];
        Double total = (Double) rawResults[6];

        DisplayOrderDetailDTO dto = new DisplayOrderDetailDTO(orderDetailIdOld, quantity, imgUrl, color, size, productName, total);

        return dto;
    }

    // Dùng để hiện thị order ở admin
    @Override
    public List<DisplayOrderDetailDTO> showOrderDetailAdmin(Long orderId) {
        String jpql = "SELECT " +
                "od.id, " +
                "od.quantity, " +
                "pv.imageUrl, " +
                "pv.color, " +
                "pv.size, " +
                "p.name, " +
                "CASE " +
                "  WHEN pd.discountPercentage IS NOT NULL THEN (pv.price * (1.0 - (pd.discountPercentage / 100.0))) " +
                "  ELSE pv.price " +
                "END " +
                "FROM OrderDetailEntity od " +
                "LEFT JOIN od.productVariant pv " +
                "LEFT JOIN pv.product p " +
                "LEFT JOIN od.productDiscount pd " +
                "INNER JOIN od.order o " +
                "WHERE o.id = :orderId";

        List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                .setParameter("orderId", orderId)
                .getResultList();

        Map<Long, Long> quantityReturnMap = new HashMap<>();

        String query = "SELECT ro.orderDetail.id, ro.quantityReturn FROM ReturnOrderEntity ro WHERE ro.orderDetail.order.id = :orderId";
        List<Object[]> rawResultsQuantity = entityManager.createQuery(query, Object[].class)
                .setParameter("orderId", orderId)
                .getResultList();

        for (Object[] result : rawResultsQuantity) {
            Long orderDetailId = (Long) result[0];
            Long quantityReturnValue = (Long) result[1];
            quantityReturnMap.put(orderDetailId, quantityReturnValue);
        }

        List<DisplayOrderDetailDTO> resultList = new ArrayList<>();

        for (Object[] result : rawResults) {
            Long orderDetailId = (Long) result[0];
            Integer quantityInteger = (Integer) result[1];
            Long quantity = Long.valueOf(quantityInteger);
            String imgUrl = (String) result[2];
            String color = (String) result[3];
            String size = (String) result[4];
            String productName = (String) result[5];
            Double total = (Double) result[6];

            if(quantity == 0) {
                quantity = quantityReturnMap.get(orderDetailId);
            }

            resultList.add(new DisplayOrderDetailDTO(orderDetailId, quantity ,imgUrl, color, size, productName, total));
        }

        return resultList;
    }
}


