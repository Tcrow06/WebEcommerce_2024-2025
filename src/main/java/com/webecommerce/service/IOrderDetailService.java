package com.webecommerce.service;

import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailDTO> findAllByOrderId(Long orderId);
    OrderDetailDTO findProductVariantById(Long orderId);
    List<DisplayOrderDetailDTO> showOrderDetail(Long orderId);
    DisplayOrderDetailDTO findOrderDetail(Long orderDetailId);

}
