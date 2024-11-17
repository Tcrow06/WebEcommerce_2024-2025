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
    public boolean changeStatus(Long orderDetailId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            String findOrderIdQuery = "SELECT od.order.id FROM OrderDetailEntity od WHERE od.id = :orderDetailId";
            Query findOrderId = entityManager.createQuery(findOrderIdQuery);
            findOrderId.setParameter("orderDetailId", orderDetailId);

            Long orderId = (Long) findOrderId.getSingleResult();

            OrderStatusEntity newOrderStatus = new OrderStatusEntity();
            newOrderStatus.setOrder(orderDAO.findById(orderId));
            newOrderStatus.setStatus(EnumOrderStatus.valueOf("WAITING"));
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
}

