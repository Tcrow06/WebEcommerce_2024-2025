package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.entity.order.OrderEntity;

import java.util.List;

public interface IOrderDAO extends GenericDAO<OrderEntity> {
    List<DisplayOrderDTO> getOrderDisplay(Long customerId);
}
