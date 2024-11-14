package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.order.OrderStatusEntity;

import java.util.List;

public interface IOrderStatusDAO extends GenericDAO<OrderStatusEntity> {
    List<OrderStatusEntity> getDeliveredOrdersByCustomerId(Long idUser);
}
