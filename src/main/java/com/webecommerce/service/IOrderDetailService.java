package com.webecommerce.service;

import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailDTO> findAllByOrderId(Long orderId);
    OrderDetailDTO findProductVariantById(Long orderId);
}
