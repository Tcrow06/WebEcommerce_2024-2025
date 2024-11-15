package com.webecommerce.service;

import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.entity.order.OrderInfoEntity;

import java.util.List;

public interface IOrderInfoService {

    OrderInfoDTO getOrderInfoById(Long orderInfoId);

    OrderInfoDTO addOrderInfo(OrderInfoDTO orderInfo);

    OrderInfoDTO updateOrderInfo(OrderInfoDTO orderInfo);

    OrderInfoDTO getOrderInfoDefault(Long customerId);

    boolean deleteOrderInfo(Long orderInfoId);

    boolean setOrderInfoDefault(OrderInfoDTO orderInfo);

    List<OrderInfoDTO> getAllOrderInfos();

    List<OrderInfoDTO> getOrderInfoByCustomerId(Long customerId);
}
