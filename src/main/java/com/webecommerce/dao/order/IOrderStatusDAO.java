package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.order.OrderStatusEntity;

public interface IOrderStatusDAO extends GenericDAO<OrderStatusEntity> {
    boolean changeStatus(Long orderDetailId);
}
