package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.entity.order.OrderStatusEntity;

import javax.persistence.Query;

public class OrderStatusDAO extends AbstractDAO<OrderStatusEntity> implements IOrderStatusDAO {
    public OrderStatusDAO() {
        super(OrderStatusEntity.class);
    }

    @Override
    public boolean changeStatus(Long orderId) {
        String query = "UPDATE OrderStatusEntity os SET os.status = 'WAITING', os.date = CURRENT_DATE WHERE os.id = :orderId";
        Query jpqlQuery = entityManager.createQuery(query);

        jpqlQuery.setParameter("orderId", orderId);

        int rowsUpdated = jpqlQuery.executeUpdate();

        return rowsUpdated > 0;

    }
}

