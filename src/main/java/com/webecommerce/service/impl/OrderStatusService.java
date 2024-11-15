package com.webecommerce.service.impl;

import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.service.IOrderStatusService;

import javax.inject.Inject;

public class OrderStatusService implements IOrderStatusService {
    @Inject
    private IOrderStatusDAO orderStatusDAO;
    @Override
    public boolean changeStatus(Long orderId) {
        return orderStatusDAO.changeStatus(orderId);
    }
}
