package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        String jpql = """
        SELECT
            o.id AS orderId,
            MAX(os.date) AS statusDate,
            SUM(od.quantity * pv.price *
                CASE WHEN pd IS NOT NULL 
                     THEN (1 - pd.discountPercentage / 100) 
                     ELSE 1 
                END) AS totalOrder,
            SUM(od.quantity) AS allQuantity,
            (SELECT pvRep.imageUrl
             FROM com.webecommerce.entity.product.ProductVariantEntity pvRep
             JOIN com.webecommerce.entity.order.OrderDetailEntity odRep ON odRep.productVariant.id = pvRep.id
             WHERE odRep.order.id = o.id
             AND pvRep.id = (
                 SELECT MIN(pvSub.id)
                 FROM com.webecommerce.entity.product.ProductVariantEntity pvSub
                 JOIN com.webecommerce.entity.order.OrderDetailEntity odSub ON odSub.productVariant.id = pvSub.id
                 WHERE odSub.order.id = o.id
             )
            ) AS imgUrl,
            os.status AS status
        FROM com.webecommerce.entity.order.OrderEntity o
        JOIN o.orderDetails od
        JOIN od.productVariant pv
        LEFT JOIN od.productDiscount pd
        JOIN o.orderStatuses os
        WHERE os.date = (
            SELECT MAX(os2.date)
            FROM com.webecommerce.entity.order.OrderStatusEntity os2
            WHERE os2.order.id = o.id
        )
        AND o.customer.id = :customerId
        GROUP BY o.id, os.status
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
}
