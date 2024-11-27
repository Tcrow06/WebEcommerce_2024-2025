package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;
import com.webecommerce.entity.order.OrderStatusEntity;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderStatusDAO extends AbstractDAO<OrderStatusEntity> implements IOrderStatusDAO {
    public OrderStatusDAO() {
        super(OrderStatusEntity.class);
    }
    @Inject
    private IOrderDAO orderDAO;

    @Override
    public List<TransferListOderStatusDTO> getStatusOrders(Long idUser, EnumOrderStatus status) {
        String query = "SELECT o.order.id, o.date, od.productVariant.imageUrl " +
                "FROM OrderStatusEntity o " +
                "JOIN OrderDetailEntity od ON o.order.id = od.order.id " +
                "WHERE o.status = :status AND o.order.customer.id = :idUser";

        List<Object[]> result = entityManager.createQuery(query, Object[].class)
                .setParameter("status", status)
                .setParameter("idUser", idUser)
                .getResultList();

        // Nhóm dữ liệu theo `id`
        Map<Long, TransferListOderStatusDTO> groupedOrders = new LinkedHashMap<>();
        for (Object[] row : result) {
            Long id = (Long) row[0];
            LocalDateTime date = (LocalDateTime) row[1];
            String img = (String) row[2];

            groupedOrders.computeIfAbsent(id, key -> new TransferListOderStatusDTO(id, date, new ArrayList<>()))
                    .addImg(img);
        }

        return new ArrayList<>(groupedOrders.values());
    }

    @Override
    public boolean changeStatus(Long orderDetailId, EnumOrderStatus status) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            String findOrderIdQuery = "SELECT od.order.id FROM OrderDetailEntity od WHERE od.id = :orderDetailId";
            Query findOrderId = entityManager.createQuery(findOrderIdQuery);
            findOrderId.setParameter("orderDetailId", orderDetailId);

            Long orderId = (Long) findOrderId.getSingleResult();

            String checkStatusQuery = "SELECT COUNT(os) FROM OrderStatusEntity os WHERE os.order.id = :orderId AND os.status = :status";
            Query checkStatus = entityManager.createQuery(checkStatusQuery);
            checkStatus.setParameter("orderId", orderId);
            checkStatus.setParameter("status", status);

            long existingStatusCount = (long) checkStatus.getSingleResult();
            if (existingStatusCount == 0) {
                OrderStatusEntity newOrderStatus = new OrderStatusEntity();
                newOrderStatus.setOrder(orderDAO.findById(orderId));
                newOrderStatus.setStatus(status);
                newOrderStatus.setDate(LocalDateTime.now());

                entityManager.persist(newOrderStatus);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}

