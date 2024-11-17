package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.entity.order.OrderDetailEntity;

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
    public OrderDetailEntity findProductVariantById(Long orderId) {
        String query = "SELECT od FROM OrderDetailEntity od WHERE od.id = :orderId";
        return entityManager.createQuery(query, OrderDetailEntity.class).getSingleResult();
    }
}
