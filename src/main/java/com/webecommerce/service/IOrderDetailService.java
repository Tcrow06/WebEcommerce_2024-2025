package com.webecommerce.service;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailDTO> findAllByOrderId(Long orderId);
    List<DisplayOrderDetailDTO> showOrderDetail(Long orderId, EnumOrderStatus status);
    DisplayOrderDetailDTO findOrderDetail(Long orderDetailId);
    EnumOrderStatus getCurrentStatus(Long orderId);

}
