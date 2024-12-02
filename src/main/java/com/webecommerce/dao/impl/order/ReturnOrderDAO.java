package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.dao.order.IReturnOrderDAO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReturnOrderDAO extends AbstractDAO<ReturnOrderEntity> implements IReturnOrderDAO {
    @Inject
    private IOrderDAO orderDAO;
    public ReturnOrderDAO() {
        super(ReturnOrderEntity.class);
    }

    @Override
    public List<Object[]> getData() {
        String jpql = "SELECT ro.orderDetail.id, ro.returnDate, ro.status, ro.quantityReturn, pv.color, p.name " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.productVariant pv " +
                "JOIN pv.product p";

        return entityManager.createQuery(jpql, Object[].class).getResultList();
    }

    @Override
    public Object[] getCustomerByReturnOrderId(Long returnOrderId) {
        String jpql = "SELECT c.id, c.name, c.phone, c.email " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.order o " +
                "JOIN o.customer c " +
                "WHERE ro.orderDetail.id = :returnOrderId";

        Object[] result = entityManager.createQuery(jpql, Object[].class)
                .setParameter("returnOrderId", returnOrderId)
                .getSingleResult();
        return result;
    }

    @Override
    public List<Object[]> getProductReturnData(Long returnOrderId) {
        String jpql = "SELECT ro.orderDetail.id, ro.returnDate, ro.status, ro.quantityReturn, " +
                "pv.color, pv.imageUrl, pv.size, p.name, pv.price, " +
                "pd.discountPercentage, ro.reason " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.productVariant pv " +
                "JOIN pv.product p " +
                "LEFT JOIN p.productDiscount pd " +
                "WHERE ro.orderDetail.id = :returnOrderId";

        // Execute the JPQL query
        List<Object[]> result = entityManager.createQuery(jpql, Object[].class)
                .setParameter("returnOrderId", returnOrderId)  // Bind the returnOrderId parameter
                .getResultList();

        return result;
    }

    @Override
    public long updateStatus(Long returnOrderId) {
        try {
            //Tim orderId
            String findOrderIdQuery = "SELECT od.order.id FROM OrderDetailEntity od WHERE od.id = :orderDetailId";
            Query findOrderId = entityManager.createQuery(findOrderIdQuery);
            findOrderId.setParameter("orderDetailId", returnOrderId);

            Long orderId = (Long) findOrderId.getSingleResult();

            String checkQuantityOrderQuery = "SELECT COUNT(od), SUM(od.quantity) FROM OrderDetailEntity od WHERE od.order.id = :orderId";

            Query checkQuantityOrder = entityManager.createQuery(checkQuantityOrderQuery);
            checkQuantityOrder.setParameter("orderId", orderId);

            Object[] result = (Object[]) checkQuantityOrder.getSingleResult();
            Long countOrderDetail = (Long) result[0];
            Long totalQuantityOrderDetail = (Long) result[1];

            // Kiểm tra số lượng mặt hàng đặt và số lượng mặt hàng trả
            String checkQuantityReturnQuery = "SELECT COUNT(ro), SUM(ro.quantityReturn) FROM ReturnOrderEntity ro WHERE ro.orderDetail.order.id = :orderId AND ro.status != 0";

            Query checkQuantityReturn = entityManager.createQuery(checkQuantityReturnQuery);
            checkQuantityReturn.setParameter("orderId", orderId);

            Object[] result2 = (Object[]) checkQuantityReturn.getSingleResult();
            Long countReturnOrder = (Long) result2[0];
            Long totalReturnOrderQuantity = (Long) result2[1];


            // Nếu không có trạng thái khác 1, thực hiện cập nhật trạng thái
            if (Objects.equals(countReturnOrder, countOrderDetail) && totalQuantityOrderDetail == 0) {
                String checkProcessedStatusQuery = "SELECT COUNT(os) FROM OrderStatusEntity os WHERE os.order.id = :orderId AND os.status = :status";
                Query checkProcessedStatus = entityManager.createQuery(checkProcessedStatusQuery);
                checkProcessedStatus.setParameter("orderId", orderId);
                checkProcessedStatus.setParameter("status", EnumOrderStatus.valueOf("CANCELLED"));

                return (long) checkProcessedStatus.getSingleResult();
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean updateStatusOrder(Long orderDetailId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String query = "UPDATE ReturnOrderEntity ro SET ro.status = 1 WHERE ro.orderDetail.id = :orderDetailId";
            Query jpqlQuery = entityManager.createQuery(query);
            jpqlQuery.setParameter("orderDetailId", orderDetailId);
            int update = jpqlQuery.executeUpdate();

            if (update == 0) {
                transaction.rollback();
                return false;
            }

            String selectQuery = "SELECT ro.quantityReturn, od.productVariant.id " +
                    "FROM ReturnOrderEntity ro " +
                    "JOIN ro.orderDetail od " +
                    "WHERE ro.orderDetail.id = :orderDetailId";
            Object[] result = (Object[]) entityManager.createQuery(selectQuery)
                    .setParameter("orderDetailId", orderDetailId)
                    .getSingleResult();

            Long quantityReturnL = (Long) result[0];
            Long productVariantId = (Long) result[1];
            Integer quantityReturn = quantityReturnL.intValue();

            if (productVariantId != null) {
                // Tăng lại sản phẩm
                String updateQuery = "UPDATE ProductVariantEntity pv SET pv.quantity = pv.quantity + :quantityReturn WHERE pv.id = :productVariantId";
                int productUpdateCount = entityManager.createQuery(updateQuery)
                        .setParameter("quantityReturn", quantityReturn)
                        .setParameter("productVariantId", productVariantId)
                        .executeUpdate();

                //Giảm sản phẩm trong đơn
                String updateQuantityQuery = "UPDATE OrderDetailEntity od SET od.quantity = od.quantity - :quantityReturn WHERE od.id = :orderDetailId";
                int orderUpdateCount = entityManager.createQuery(updateQuantityQuery)
                        .setParameter("orderDetailId", orderDetailId)
                        .setParameter("quantityReturn", quantityReturn)
                        .executeUpdate();

                if (productUpdateCount == 0 && orderUpdateCount == 0) {
                    transaction.rollback();
                    return false;
                }
            }

            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

//    @Override
//    public boolean updateStatusNoReturn(Long orderDetailId) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//
//            String query = "UPDATE ReturnOrderEntity ro SET ro.status = 2 WHERE ro.orderDetail.id = :orderDetailId";
//            Query jpqlQuery = entityManager.createQuery(query);
//            jpqlQuery.setParameter("orderDetailId", orderDetailId);
//            int rowsUpdated = jpqlQuery.executeUpdate();
//
//            if (rowsUpdated == 0) {
//                transaction.rollback();
//                return false;
//            }
//
//            transaction.commit();
//            return true;
//        }
//        catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            return false;
//        }
//    }

    @Override
    public long updateStatusProcess(Long orderDetailId) {
        try {
            //Tim orderId
            String findOrderIdQuery = "SELECT od.order.id FROM OrderDetailEntity od WHERE od.id = :orderDetailId";
            Query findOrderId = entityManager.createQuery(findOrderIdQuery);
            findOrderId.setParameter("orderDetailId", orderDetailId);

            Long orderId = (Long) findOrderId.getSingleResult();

            String checkOrderSuccess = "SELECT COUNT(od) FROM OrderDetailEntity od " +
                    "WHERE od.order.id = :orderId " +
                    "AND od.id IN (SELECT ro.orderDetail.id FROM ReturnOrderEntity ro WHERE ro.status = 0)";

            Query checkSuccess = entityManager.createQuery(checkOrderSuccess);
            checkSuccess.setParameter("orderId", orderId);


            long countSuccess = (long) checkSuccess.getSingleResult();

            // Neu khong co don hoan tra nao chua xu li
            if (countSuccess == 0) {
                String checkProcessedStatusQuery = "SELECT COUNT(os) FROM OrderStatusEntity os WHERE os.order.id = :orderId AND os.status = :status";
                Query checkProcessedStatus = entityManager.createQuery(checkProcessedStatusQuery);
                checkProcessedStatus.setParameter("orderId", orderId);
                checkProcessedStatus.setParameter("status", EnumOrderStatus.PROCESSED);

                return (long) checkProcessedStatus.getSingleResult();
            }
            return -1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
