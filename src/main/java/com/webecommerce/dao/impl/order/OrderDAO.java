package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Map;
import javax.persistence.Query;
import java.util.logging.Level;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO extends AbstractDAO<OrderEntity> implements IOrderDAO {

    public OrderDAO() {
        super(OrderEntity.class);
    }

    @Override
    public List<DisplayOrderDTO> getOrderDisplay(Long customerId) {
        try {
            String jpql = """
        SELECT\s
            o.id AS orderId,
            MAX(os.date) AS statusDate,
            SUM(od.quantity * pv.price *\s
                CASE\s
                    WHEN pd IS NOT NULL THEN (1 - pd.discountPercentage / 100)\s
                    ELSE 1\s
                END) AS totalOrder,
            SUM(od.quantity) AS allQuantity,
            MIN(pv.imageUrl) AS imgUrl,
            os.status AS status
        FROM\s
            OrderEntity o
        JOIN\s
            o.orderDetails od
        JOIN\s
            od.productVariant pv
        LEFT JOIN\s
            od.productDiscount pd
        JOIN\s
            o.orderStatuses os
        WHERE\s
            os.date = (
                SELECT MAX(os2.date)
                FROM OrderStatusEntity os2
                WHERE os2.order.id = o.id
            )
        AND\s
            o.customer.id = :customerId
        GROUP BY\s
            o.id, os.status
    """;

            List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                    .setParameter("customerId", customerId)
                    .getResultList();

            List<DisplayOrderDTO> resultList = new ArrayList<>();

            for (Object[] result : rawResults) {
                Long orderId = (Long) result[0];
                LocalDateTime statusDate = (LocalDateTime) result[1]; // Chuyển từ Timestamp thành LocalDateTime
                Double totalOrder = (Double) result[2];
                Long allQuantity = ((Number) result[3]).longValue(); // Convert từ Number thành Long
                String imgUrl = (String) result[4];
                EnumOrderStatus status = (EnumOrderStatus) result[5]; // Chỉ cần ép kiểu trực tiếp

                // Tạo DisplayOrderDTO và thêm vào danh sách
                resultList.add(new DisplayOrderDTO(orderId, statusDate, totalOrder, allQuantity, imgUrl, status));
            }

            return resultList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public OrderEntity merge(OrderEntity orderEntity) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            // Sử dụng merge() thay vì persist()
            em.persist(orderEntity);  // Nếu entity đã tồn tại, nó sẽ được cập nhật; nếu chưa tồn tại, sẽ được chèn mới
            em.flush();  // Đảm bảo dữ liệu được ghi vào DB
            em.clear();  // Làm trống bộ nhớ đệm sau khi ghi
            trans.commit();  // Commit giao dịch
            return null;
//            LOGGER.log(Level.INFO, "Merged object: {0}", mergedEntity);
//            return mergedEntity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error merging object", e);
            trans.rollback();  // Rollback nếu có lỗi
            return null;
        } finally {
            em.close();  // Đóng EntityManager sau khi hoàn tất
        }
    }

    @Override
    public List<ProductVariantEntity> getBestSellingProduct() {


        return null;
    }

    @Override
    public List<Object[]> calculateMonthlyRevenue(int year) {
        // Đặt lại biến đúng tên cho câu truy vấn JPQL
        String jbql = "SELECT MONTH(os.date) AS month, SUM(od.quantity * pv.price) AS totalRevenue " +
                "FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "JOIN o.orderDetails od " +
                "JOIN od.productVariant pv " +
                "WHERE os.status = :status " +
                "AND YEAR(os.date) = :year " +
                "GROUP BY MONTH(os.date) " +
                "ORDER BY MONTH(os.date)";

        // Tạo TypedQuery với câu truy vấn đúng
        TypedQuery<Object[]> query = entityManager.createQuery(jbql, Object[].class);

        // Thiết lập tham số năm
        query.setParameter("year", year);
        query.setParameter("status", EnumOrderStatus.RECEIVED);

        // Trả về kết quả
        return query.getResultList();
    }

    @Override
    public Double calculateTotalRevenueByYear(int year) {
        // Câu truy vấn JPQL để tính tổng doanh thu theo năm
        String jpql = "SELECT SUM(od.quantity * pv.price) AS totalRevenue " +
                "FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "JOIN o.orderDetails od " +
                "JOIN od.productVariant pv " +
                "WHERE os.status = :status " +
                "AND YEAR(os.date) = :year";

        // Tạo TypedQuery với câu truy vấn đúng
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);

        // Thiết lập tham số năm và trạng thái
        query.setParameter("year", year);
        query.setParameter("status", EnumOrderStatus.RECEIVED);

        // Lấy kết quả
        Double totalRevenue = query.getSingleResult();

        // Nếu không có dữ liệu, trả về 0.0 thay vì null
        return totalRevenue != null ? totalRevenue : 0.0;
    }
    @Override
    public Double calculateTotalRevenue() {
        // Câu truy vấn JPQL để tính toàn bộ doanh thu
        String jpql = "SELECT SUM(od.quantity * pv.price) AS totalRevenue " +
                "FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "JOIN o.orderDetails od " +
                "JOIN od.productVariant pv";

        // Tạo TypedQuery với câu truy vấn đúng
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);

        // Lấy kết quả
        Double totalRevenue = query.getSingleResult();

        // Nếu không có dữ liệu, trả về 0.0 thay vì null
        return totalRevenue != null ? totalRevenue : 0.0;
    }

    @Override
    public int totalOrders() {
        String query = "SELECT COUNT(p) FROM OrderEntity p"; // Đếm tổng số sản phẩm
        try {
            Long count = entityManager.createQuery(query, Long.class)
                    .getSingleResult();
            return count != null ? count.intValue() : 0; // Chuyển đổi Long thành int
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tính tổng số sản phẩm", e);
            return 0; // Trả về 0 nếu xảy ra lỗi
        }
    }
    @Override
    public int totalOrdersToday() {
        String query = "SELECT COUNT(o) " +
                "FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "WHERE DATE(os.date) = CURRENT_DATE";

        try {
            Long count = entityManager.createQuery(query, Long.class)
                    .getSingleResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tính tổng số đơn hàng trong ngày", e);
            return 0;
        }
    }

    public boolean changeConfirmStatus(Long orderId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            OrderStatusEntity newOrderStatus = new OrderStatusEntity();
            newOrderStatus.setOrder(findById(orderId));
            newOrderStatus.setStatus(EnumOrderStatus.valueOf("DELIVERED"));
            newOrderStatus.setDate(LocalDateTime.now());

            entityManager.persist(newOrderStatus);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DisplayOrderDTO> getListOrder() {
        try {
            String jpql = """
        SELECT\s
            o.id AS orderId,
            MAX(os.date) AS statusDate,
            SUM(od.quantity * pv.price *\s
                CASE\s
                    WHEN pd IS NOT NULL THEN (1 - pd.discountPercentage / 100)\s
                    ELSE 1\s
                END) AS totalOrder,
            SUM(od.quantity) AS allQuantity,
            MIN(pv.imageUrl) AS imgUrl
        FROM\s
            OrderEntity o
        JOIN\s
            o.orderDetails od
        JOIN\s
            od.productVariant pv
        LEFT JOIN\s
            od.productDiscount pd
        JOIN\s
            o.orderStatuses os
        WHERE\s
            os.date = (
                SELECT MAX(os2.date)
                FROM OrderStatusEntity os2
                WHERE os2.order.id = o.id AND os2.status = :status
            )
        GROUP BY\s
            o.id
    """;

            List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                    .setParameter("status",EnumOrderStatus.PENDING)
                    .getResultList();

            List<DisplayOrderDTO> resultList = new ArrayList<>();

            for (Object[] result : rawResults) {
                Long orderId = (Long) result[0];
                LocalDateTime statusDate = (LocalDateTime) result[1];
                Double totalOrder = (Double) result[2];
                Long allQuantity = ((Number) result[3]).longValue();
                String imgUrl = (String) result[4];

                resultList.add(new DisplayOrderDTO(orderId, statusDate, totalOrder, allQuantity, imgUrl));
            }

            return resultList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
