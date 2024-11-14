package com.webecommerce.service;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;


import java.util.List;
public interface IOrderStatusService {
    List<OrderStatusDTO> getDeliveredOrdersByCustomerId(Long customerId);
}
