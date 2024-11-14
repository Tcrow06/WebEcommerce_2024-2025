package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.order.OrderInfoDAO;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.service.IOrderInfoService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class OrderInfoService implements IOrderInfoService {

    @Inject
    private OrderInfoDAO orderInfoDAO;

    @Override
    public OrderInfoEntity addOrderInfo(OrderInfoEntity orderInfo) {
        return orderInfoDAO.insert(orderInfo);
    }

    @Override
    public OrderInfoEntity updateOrderInfo(OrderInfoEntity orderInfo) {
        OrderInfoEntity oldOrderInfo = orderInfoDAO.findById(orderInfo.getId());
        if (oldOrderInfo != null) {
            return orderInfoDAO.update(orderInfo);
        }
        return null;
    }

    @Override
    public OrderInfoEntity getOrderInfoDefault(Long id) {
        List<OrderInfoEntity> orderInfos = getAllOrderInfos();
        Optional<OrderInfoEntity> orderInfoDefault = orderInfos.stream()
                .filter(orderInfo -> orderInfo.getIsDefault() == 1)
                .findFirst();
        return orderInfoDefault.isPresent() ? orderInfoDefault.get() : null;
    }

    @Override
    public boolean deleteOrderInfo(Long id) {
        return orderInfoDAO.delete(id);
    }

    @Override
    public List<OrderInfoEntity> getAllOrderInfos() {
        return orderInfoDAO.findAll();
    }
}
