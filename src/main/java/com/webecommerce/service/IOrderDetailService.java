package com.webecommerce.service;

import com.webecommerce.dto.OrderDetailDTO;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailDTO> findAllByOrderId(Long orderId);
}
