package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IReturnOrderDAO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.order.ReturnOrderEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturnOrderDAO extends AbstractDAO<ReturnOrderEntity> implements IReturnOrderDAO {
    public ReturnOrderDAO() {
        super(ReturnOrderEntity.class);
    }

    @Override
    public List<TransferListDTO> getData() {
        String jpql = "SELECT ro.id, ro.returnDate, ro.status, ro.quantityReturn, pv.color, p.name " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.productVariant pv " +
                "JOIN pv.product p";

        // Thực thi câu lệnh JPQL thông qua EntityManager
        List<Object[]> result = entityManager.createQuery(jpql, Object[].class).getResultList();

        // Khởi tạo DTO để chứa dữ liệu
        // Duyệt qua kết quả và chuyển đổi thành DTO
        List<TransferListDTO> transferItems = new ArrayList<>();
        for (Object[] row : result) {
            Long id = (Long) row[0];
            LocalDate returnDate = (LocalDate) row[1];
            int status = (Integer) row[2];
            Long quantityReturn = (Long) row[3];
            String color = (String) row[4];
            String productName = (String) row[5];

            // Tạo đối tượng TransferItemDTO từ các trường dữ liệu
            TransferListDTO item = new TransferListDTO(id, returnDate, productName,color, quantityReturn , status);
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
                "WHERE ro.id = :returnOrderId";

        // Execute the JPQL query
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
        String jpql = "SELECT ro.id, ro.returnDate, ro.status, ro.quantityReturn, " +
                "pv.color, pv.imageUrl, pv.size, p.name, pv.price, " +
                "pd.discountPercentage, ro.reason " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.productVariant pv " +
                "JOIN pv.product p " +
                "LEFT JOIN p.productDiscount pd " +
                "WHERE ro.id = :returnOrderId";

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
        String query = "UPDATE ReturnOrderEntity ro SET ro.status = 1 WHERE ro.id = :returnOrderId";
        Query jpqlQuery = entityManager.createQuery(query);

        jpqlQuery.setParameter("returnOrderId", returnOrderId);

        int rowsUpdated = jpqlQuery.executeUpdate();

        return rowsUpdated > 0;
    }

    @Override
    public boolean updateProductVariantQuantity(Long returnOrderId) {
        String selectQuery = "SELECT ro.quantityReturn, od.productVariant.id " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "WHERE ro.id = :returnOrderId";
        Object[] result = (Object[]) entityManager.createQuery(selectQuery)
                .setParameter("returnOrderId", returnOrderId)
                .getSingleResult();

        Integer quantityReturn = (Integer) result[0];
        Long productVariantId = (Long) result[1];

        if (quantityReturn != null && productVariantId != null) {
            String updateQuery = "UPDATE ProductVariantEntity pv SET pv.quantity = pv.quantity + :quantityReturn WHERE pv.id = :productVariantId";
            int rowsUpdated = entityManager.createQuery(updateQuery)
                    .setParameter("quantityReturn", quantityReturn)
                    .setParameter("productVariantId", productVariantId)
                    .executeUpdate();
            return rowsUpdated > 0;
        }
        return false;
    }

    @Override
    public boolean updateStatusOrder(Long returnOrderId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // Bắt đầu giao dịch
            transaction.begin();

            // Cập nhật trạng thái của đơn hàng trong bảng ReturnOrderEntity
            String query = "UPDATE ReturnOrderEntity ro SET ro.status = 1 WHERE ro.id = :returnOrderId";
            Query jpqlQuery = entityManager.createQuery(query);
            jpqlQuery.setParameter("returnOrderId", returnOrderId);
            int rowsUpdated = jpqlQuery.executeUpdate();

            if (rowsUpdated == 0) {
                transaction.rollback();
                return false; // Nếu không cập nhật được trạng thái, rollback
            }

            // Cập nhật số lượng sản phẩm trong bảng ProductVariantEntity
            String selectQuery = "SELECT ro.quantityReturn, od.productVariant.id " +
                    "FROM ReturnOrderEntity ro " +
                    "JOIN ro.orderDetail od " +
                    "WHERE ro.id = :returnOrderId";
            Object[] result = (Object[]) entityManager.createQuery(selectQuery)
                    .setParameter("returnOrderId", returnOrderId)
                    .getSingleResult();

            Long quantityReturnL = (Long) result[0];
            Long productVariantId = (Long) result[1];
            Integer quantityReturn = quantityReturnL.intValue();

            if (quantityReturn != null && productVariantId != null) {
                String updateQuery = "UPDATE ProductVariantEntity pv SET pv.quantity = pv.quantity + :quantityReturn WHERE pv.id = :productVariantId";
                int productUpdateCount = entityManager.createQuery(updateQuery)
                        .setParameter("quantityReturn", quantityReturn)
                        .setParameter("productVariantId", productVariantId)
                        .executeUpdate();

                if (productUpdateCount == 0) {
                    transaction.rollback();
                    return false; // Nếu không cập nhật được số lượng sản phẩm, rollback
                }
            }

            // Cập nhật trạng thái trong bảng OrderStatusEntity
            String jpql = "UPDATE OrderStatusEntity os " +
                    "SET os.status = 'DELIVERED', os.date = CURRENT_DATE " +
                    "WHERE os.order.id = (SELECT od.order.id " +
                    "FROM ReturnOrderEntity ro " +
                    "JOIN ro.orderDetail od " +
                    "WHERE ro.id = :returnOrderId)";
            int orderStatusUpdated = entityManager.createQuery(jpql)
                    .setParameter("returnOrderId", returnOrderId)
                    .executeUpdate();

            if (orderStatusUpdated == 0) {
                transaction.rollback();
                return false; // Nếu không cập nhật được trạng thái đơn hàng, rollback
            }

            // Commit giao dịch nếu tất cả đều thành công
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            // Nếu có lỗi, rollback giao dịch
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;  // Ném lại exception
        }
    }


}
