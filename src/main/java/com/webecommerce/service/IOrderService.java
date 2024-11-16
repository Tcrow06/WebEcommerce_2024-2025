package com.webecommerce.service;

import com.webecommerce.dto.notinentity.DisplayOrderDTO;

import java.util.List;

public interface IOrderService {
    List<DisplayOrderDTO> getOrderDisplay(Long customerId);
}
