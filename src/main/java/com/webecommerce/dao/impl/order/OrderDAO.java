package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.entity.order.OrderEntity;

public class OrderDAO extends AbstractDAO<OrderEntity> implements IOrderDAO {

    public OrderDAO() {
        super(OrderEntity.class);
    }
}
