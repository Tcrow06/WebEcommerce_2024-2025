package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.entity.order.OrderStatusEntity;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;

public class OrderStatusDAO extends AbstractDAO<OrderStatusEntity> implements IOrderStatusDAO {
    public OrderStatusDAO() {
        super(OrderStatusEntity.class);
    }
    @Inject
    private IOrderDAO orderDAO;

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

