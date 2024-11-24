package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        if (status.equals(EnumOrderStatus.PROCESSED)) {
            jpql += " AND EXISTS (SELECT 1 FROM ReturnOrderEntity ro WHERE ro.orderDetail.id = od.id AND ro.status = 2)";
        }

        List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                .setParameter("orderId", orderId)
                .getResultList();

        List<DisplayOrderDetailDTO> resultList = new ArrayList<>();

        for (Object[] result : rawResults) {
            Long orderDetailId = (Long) result[0];
            Integer quantity  = (Integer) result[1];
            String imgUrl = (String) result[2];
            String color = (String) result[3];
            String size = (String) result[4];
            String productName = (String) result[5];
            Double total = (Double) result[6];

            resultList.add(new DisplayOrderDetailDTO(orderDetailId, quantity,imgUrl, color, size, productName, total));
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
        Integer quantity  = (Integer) rawResults[1];
        String imgUrl = (String) rawResults[2];
        String color = (String) rawResults[3];
        String size = (String) rawResults[4];
        String productName = (String) rawResults[5];
        Double total = (Double) rawResults[6];

        DisplayOrderDetailDTO dto = new DisplayOrderDetailDTO(orderDetailIdOld, quantity, imgUrl, color, size, productName, total);

        return dto;
    }
}


