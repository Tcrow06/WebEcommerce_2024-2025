package com.webecommerce.service.impl;

import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.service.IOrderService;

import javax.inject.Inject;
import java.util.List;

public class OrderService implements IOrderService {
    @Inject
    private IOrderDAO orderDAO;
    @Override
    public List<DisplayOrderDTO> getOrderDisplay(Long customerId) {
        return orderDAO.getOrderDisplay(customerId);
    }
}
