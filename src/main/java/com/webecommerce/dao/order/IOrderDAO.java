package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.order.OrderEntity;

public interface IOrderDAO extends GenericDAO<OrderEntity> {

    OrderEntity merge(OrderEntity orderEntity);

}
