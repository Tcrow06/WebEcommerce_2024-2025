package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.entity.order.OrderDetailEntity;

public class OrderDetailDAODAO extends AbstractDAO<OrderDetailEntity> implements IOrderDetailDAO {
    public OrderDetailDAODAO() {
        super(OrderDetailEntity.class);
    }
}
