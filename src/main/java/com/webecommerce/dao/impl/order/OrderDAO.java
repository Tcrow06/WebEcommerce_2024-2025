package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

import java.security.Timestamp;
import java.time.LocalDateTime;
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


            // Dùng để load lại dữ liệu cũ ở phần "Đã hủy"
            String query = """
        SELECT\s
            o.id AS orderId,
            SUM(ro.quantityReturn * pv.price *\s
                CASE\s
                    WHEN pd IS NOT NULL THEN (1 - pd.discountPercentage / 100)\s
                    ELSE 1\s
                END) AS totalOrder,
            SUM(ro.quantityReturn) AS allQuantity
        FROM\s
            ReturnOrderEntity ro
        JOIN\s
            ro.orderDetail od
        JOIN\s
            od.order o
        JOIN\s
            od.productVariant pv
        LEFT JOIN\s
            od.productDiscount pd
        WHERE\s
            o.customer.id = :customerId
        GROUP BY\s
        o.id
    """;

            List<Object[]> rawOldResult = entityManager.createQuery(query, Object[].class)
                    .setParameter("customerId", customerId)
                    .getResultList();

            List<Object[]> savedData = new ArrayList<>();

            for (Object[] result : rawOldResult) {
                Long orderId = (Long) result[0];
                Double totalOrder = (Double) result[1];
                Long allQuantity = ((Number) result[2]).longValue();

                savedData.add(new Object[]{orderId, totalOrder, allQuantity});
            }

            List<DisplayOrderDTO> resultList = new ArrayList<>();

            for (Object[] result : rawResults) {
                Long orderId = (Long) result[0];
                LocalDateTime statusDate = (LocalDateTime) result[1];
                Double totalOrder = (Double) result[2];
                Long allQuantity = ((Number) result[3]).longValue();
                String imgUrl = (String) result[4];
                EnumOrderStatus status = (EnumOrderStatus) result[5];

                if(allQuantity == 0) {
                    for (Object[] saved : savedData) {
                        Long savedOrderId = (Long) saved[0];
                        if (savedOrderId.equals(orderId)) {
                            totalOrder = (Double) saved[1];
                            allQuantity = ((Number) saved[2]).longValue();
                            break;
                        }
                    }
                }

                resultList.add(new DisplayOrderDTO(orderId, statusDate, totalOrder, allQuantity, imgUrl, status));
            }
            Collections.sort(resultList, new Comparator<DisplayOrderDTO>() {
                @Override
                public int compare(DisplayOrderDTO o1, DisplayOrderDTO o2) {
                    return o2.getDateTime().compareTo(o1.getDateTime());
                }
            });
            return resultList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DisplayOrderDTO> getOrderDisplay() {
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
        GROUP BY\s
            o.id, os.status
    """;

            List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                    .getResultList();

            //nhap

            String query = """
        SELECT\s
            o.id AS orderId,
            SUM(ro.quantityReturn * pv.price *\s
                CASE\s
                    WHEN pd IS NOT NULL THEN (1 - pd.discountPercentage / 100)\s
                    ELSE 1\s
                END) AS totalOrder,
            SUM(ro.quantityReturn) AS allQuantity
        FROM\s
            ReturnOrderEntity ro
        JOIN\s
            ro.orderDetail od
        JOIN\s
            od.order o
        JOIN\s
            od.productVariant pv
        LEFT JOIN\s
            od.productDiscount pd
        GROUP BY\s
        o.id
    """;

            List<Object[]> rawOldResult = entityManager.createQuery(query, Object[].class)
                    .getResultList();

            List<Object[]> savedData = new ArrayList<>();

            for (Object[] result : rawOldResult) {
                Long orderId = (Long) result[0];
                Double totalOrder = (Double) result[1];
                Long allQuantity = ((Number) result[2]).longValue();

                savedData.add(new Object[]{orderId, totalOrder, allQuantity});
            }

            //het nhap

            List<DisplayOrderDTO> resultList = new ArrayList<>();

            for (Object[] result : rawResults) {
                Long orderId = (Long) result[0];
                LocalDateTime statusDate = (LocalDateTime) result[1];
                Double totalOrder = (Double) result[2];
                Long allQuantity = ((Number) result[3]).longValue();
                String imgUrl = (String) result[4];
                EnumOrderStatus status = (EnumOrderStatus) result[5];

                if(allQuantity == 0) {
                    for (Object[] saved : savedData) {
                        Long savedOrderId = (Long) saved[0];
                        if (savedOrderId.equals(orderId)) {
                            totalOrder = (Double) saved[1];
                            allQuantity = ((Number) saved[2]).longValue();
                            break;
                        }
                    }
                }

                resultList.add(new DisplayOrderDTO(orderId, statusDate, totalOrder, allQuantity, imgUrl, status));
            }
            Collections.sort(resultList, new Comparator<DisplayOrderDTO>() {
                @Override
                public int compare(DisplayOrderDTO o1, DisplayOrderDTO o2) {
                    return o2.getDateTime().compareTo(o1.getDateTime());
                }
            });
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
            em.persist(orderEntity);
            em.flush();
            em.clear();
            trans.commit();
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error merging object", e);
            trans.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductVariantEntity> getBestSellingProduct() {


        return null;
    }

    @Override
    public List<Object[]> calculateMonthlyRevenue(int year) {
        String jbql = "SELECT MONTH(os.date) AS month, SUM(od.quantity * pv.price) AS totalRevenue " +
                "FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "JOIN o.orderDetails od " +
                "JOIN od.productVariant pv " +
                "WHERE os.status = :status " +
                "AND YEAR(os.date) = :year " +
                "GROUP BY MONTH(os.date) " +
                "ORDER BY MONTH(os.date)";

        TypedQuery<Object[]> query = entityManager.createQuery(jbql, Object[].class);

        query.setParameter("year", year);
        query.setParameter("status", EnumOrderStatus.RECEIVED);

        return query.getResultList();
    }

    @Override
    public Double calculateTotalRevenueByYear(int year) {
        String jpql = "SELECT SUM(od.quantity * pv.price) AS totalRevenue " +
                "FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "JOIN o.orderDetails od " +
                "JOIN od.productVariant pv " +
                "WHERE os.status = :status " +
                "AND YEAR(os.date) = :year";

        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);

        query.setParameter("year", year);
        query.setParameter("status", EnumOrderStatus.RECEIVED);

        Double totalRevenue = query.getSingleResult();

        return totalRevenue != null ? totalRevenue : 0.0;
    }
    @Override
    public Double calculateTotalRevenue() {
        String jpql = "SELECT SUM(od.quantity * pv.price) AS totalRevenue " +
                "FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "JOIN o.orderDetails od " +
                "JOIN od.productVariant pv " +  // Thêm khoảng trắng ở cuối
                "WHERE os.status = :status";


        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
        query.setParameter("status",EnumOrderStatus.RECEIVED);

        Double totalRevenue = query.getSingleResult();

        return totalRevenue != null ? totalRevenue : 0.0;
    }

    @Override
    public int totalOrdersByStatus(EnumOrderStatus status) {
        String query = "SELECT COUNT(o) FROM OrderEntity o " +
                "JOIN o.orderStatuses os " +
                "WHERE os.status = :status "; // Đếm tổng số sản phẩm
        try {
            TypedQuery<Long> typedQuery = entityManager.createQuery(query, Long.class);
            typedQuery.setParameter("status",status);
            Long count = typedQuery.getSingleResult();
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
                "WHERE DATE(os.date) = CURRENT_DATE " +
                "AND os.status  =:status";

        try {
            Long count = entityManager.createQuery(query, Long.class)
                    .setParameter("status",EnumOrderStatus.PENDING)
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

            String checkStatusQuery = "SELECT COUNT(os) FROM OrderStatusEntity os WHERE os.order.id = :orderId AND os.status = :status";
            Query checkStatus = entityManager.createQuery(checkStatusQuery);
            checkStatus.setParameter("orderId", orderId);
            checkStatus.setParameter("status", EnumOrderStatus.CANCELLED);

            long existingStatusCount = (long) checkStatus.getSingleResult();

            if (existingStatusCount == 0) {
                OrderStatusEntity newOrderStatus = new OrderStatusEntity();
                newOrderStatus.setOrder(findById(orderId));
                newOrderStatus.setStatus(EnumOrderStatus.DELIVERED);
                newOrderStatus.setDate(LocalDateTime.now());
                entityManager.persist(newOrderStatus);
                transaction.commit();
                return true;
            }
            return false;
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

            // Hiển thị dữ liệu bị hủy

            String query = """
        SELECT\s
            o.id AS orderId,
            SUM(ro.quantityReturn * pv.price *\s
                CASE\s
                    WHEN pd IS NOT NULL THEN (1 - pd.discountPercentage / 100)\s
                    ELSE 1\s
                END) AS totalOrder,
            SUM(ro.quantityReturn) AS allQuantity
        FROM\s
            ReturnOrderEntity ro
        JOIN\s
            ro.orderDetail od
        JOIN\s
            od.order o
        JOIN\s
            od.productVariant pv
        LEFT JOIN\s
            od.productDiscount pd
        GROUP BY\s
        o.id
    """;

            List<Object[]> rawOldResult = entityManager.createQuery(query, Object[].class)
                    .getResultList();

            List<Object[]> savedData = new ArrayList<>();

            for (Object[] result : rawOldResult) {
                Long orderId = (Long) result[0];
                Double totalOrder = (Double) result[1];
                Long allQuantity = ((Number) result[2]).longValue();

                savedData.add(new Object[]{orderId, totalOrder, allQuantity});
            }

            List<DisplayOrderDTO> resultList = new ArrayList<>();

            for (Object[] result : rawResults) {
                Long orderId = (Long) result[0];
                LocalDateTime statusDate = (LocalDateTime) result[1];
                Double totalOrder = (Double) result[2];
                Long allQuantity = ((Number) result[3]).longValue();
                String imgUrl = (String) result[4];

                if(allQuantity == 0) {
                    for (Object[] saved : savedData) {
                        Long savedOrderId = (Long) saved[0];
                        if (savedOrderId.equals(orderId)) {
                            totalOrder = (Double) saved[1];
                            allQuantity = ((Number) saved[2]).longValue();
                            break;
                        }
                    }
                }

                resultList.add(new DisplayOrderDTO(orderId, statusDate, totalOrder, allQuantity, imgUrl));
            }

            return resultList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long findOrderId(Long orderDetailId) {
        String query = "SELECT od.order.id FROM OrderDetailEntity od WHERE od.id = :orderDetailId";
        return entityManager.createQuery(query, Long.class)
                .setParameter("orderDetailId", orderDetailId)
                .getSingleResult();
    }

}
