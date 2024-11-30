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
    public List<TransferListDTO> getData() {
        String jpql = "SELECT ro.orderDetail.id, ro.returnDate, ro.status, ro.quantityReturn, pv.color, p.name " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.productVariant pv " +
                "JOIN pv.product p";

        List<Object[]> result = entityManager.createQuery(jpql, Object[].class).getResultList();

        List<TransferListDTO> transferItems = new ArrayList<>();
        for (Object[] row : result) {
            Long id = (Long) row[0];
            LocalDate returnDate = (LocalDate) row[1];
            int status = (Integer) row[2];
            Long quantityReturn = (Long) row[3];
            String color = (String) row[4];
            String productName = (String) row[5];
            Long orderId = orderDAO.findOrderId(id);
            TransferListDTO item = new TransferListDTO(id, returnDate, productName,color, quantityReturn , status, orderId);
            transferItems.add(item);
        }
        return transferItems;
    }

    @Override
    public CustomerResponse getCustomerByReturnOrderId(Long returnOrderId) {
        String jpql = "SELECT c.id, c.name, c.phone, c.email " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.order o " +
                "JOIN o.customer c " +
                "WHERE ro.orderDetail.id = :returnOrderId";

        Object[] result = entityManager.createQuery(jpql, Object[].class)
                .setParameter("returnOrderId", returnOrderId)
                .getSingleResult();

        // Map the result to CustomerDTO
        Long customerId = (Long) result[0];
        String customerName = (String) result[1];
        String phone = (String) result[2];
        String email = (String) result[3];

        return new CustomerResponse(customerId, customerName, phone, email);
    }

    @Override
    public ProductReturnDTO getProductReturnData(Long returnOrderId) {
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

        ProductReturnDTO item = null;

        // Iterate through results and create the DTO
        for (Object[] row : result) {
            Long id = (Long) row[0];
            LocalDate returnDate = (LocalDate) row[1];
            int status = (Integer) row[2];
            Long quantityReturn = (Long) row[3];
            String color = (String) row[4];
            String imageUrl = (String) row[5];
            String size = (String) row[6];
            String productName = (String) row[7];
            double price = (Double) row[8]; // Trả về giá trị kiểu double
            Integer discountPercentage = row[9] != null ? (Integer) row[9] : 0; // If null, set default to 0.0
            String reason = (String) row[10];

            // Calculate saleProduct price directly using double values
            double discountMultiplier = 1.0 - (discountPercentage / 100.0);
            double saleProduct = price * discountMultiplier;

            // Create ProductReturnDTO with the required fields
            item = new ProductReturnDTO(
                    id, returnDate, status, quantityReturn, color, imageUrl, size, productName, saleProduct, reason);
        }

        return item;
    }

    @Override
    public boolean updateStatus(Long returnOrderId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
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

                long processedCount = (long) checkProcessedStatus.getSingleResult();

                if (processedCount == 0) {
                    OrderStatusEntity newOrderStatus = new OrderStatusEntity();
                    newOrderStatus.setOrder(orderDAO.findById(orderId));
                    newOrderStatus.setStatus(EnumOrderStatus.valueOf("CANCELLED"));
                    newOrderStatus.setDate(LocalDateTime.now());

                    entityManager.persist(newOrderStatus);

                    transaction.commit();
                    return true;
                }
            }
            transaction.commit();
            return false;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
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

    @Override
    public boolean updateStatusNoReturn(Long orderDetailId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String query = "UPDATE ReturnOrderEntity ro SET ro.status = 2 WHERE ro.orderDetail.id = :orderDetailId";
            Query jpqlQuery = entityManager.createQuery(query);
            jpqlQuery.setParameter("orderDetailId", orderDetailId);
            int rowsUpdated = jpqlQuery.executeUpdate();

            if (rowsUpdated == 0) {
                transaction.rollback();
                return false;
            }

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStatusProcess(Long orderDetailId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
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

                long processedCount = (long) checkProcessedStatus.getSingleResult();

                if (processedCount == 0) {
                    OrderStatusEntity newOrderStatus = new OrderStatusEntity();
                    newOrderStatus.setOrder(orderDAO.findById(orderId));
                    newOrderStatus.setStatus(EnumOrderStatus.valueOf("PROCESSED"));
                    newOrderStatus.setDate(LocalDateTime.now());

                    entityManager.persist(newOrderStatus);

                    transaction.commit();
                    return true;
                }
            }
            transaction.commit();
            return false;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
