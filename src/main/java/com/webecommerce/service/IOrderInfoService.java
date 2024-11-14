package com.webecommerce.service;

import com.webecommerce.entity.order.OrderInfoEntity;

import java.util.List;

public interface IOrderInfoService {

    OrderInfoEntity addOrderInfo(OrderInfoEntity orderInfo);

    OrderInfoEntity updateOrderInfo(OrderInfoEntity orderInfo);

    OrderInfoEntity getOrderInfoDefault(Long id);

    boolean deleteOrderInfo(Long id);

    List<OrderInfoEntity> getAllOrderInfos();
}
