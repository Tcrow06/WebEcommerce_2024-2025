package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.entity.order.OrderDetailEntity;

public class OrderDetailDAO extends AbstractDAO<OrderDetailEntity> implements IOrderDetailDAO {
    public OrderDetailDAO() {
        super(OrderDetailEntity.class);
    }
}
