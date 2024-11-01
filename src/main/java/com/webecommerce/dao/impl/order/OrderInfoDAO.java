package com.webecommerce.dao.impl.order;


import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderInfoDAO;
import com.webecommerce.entity.order.OrderInfoEntity;

public class OrderInfoDAO extends AbstractDAO<OrderInfoEntity> implements IOrderInfoDAO {

    public OrderInfoDAO() {
        super(OrderInfoEntity.class);
    }
}
