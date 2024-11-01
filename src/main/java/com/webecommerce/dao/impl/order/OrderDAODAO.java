package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.entity.order.OrderEntity;

public class OrderDAODAO extends AbstractDAO<OrderEntity> implements IOrderDAO {

    public OrderDAODAO() {
        super(OrderEntity.class);
    }
}
