package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.order.OrderDetailDAO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.mapper.Impl.OrderDetailMapper;
import com.webecommerce.service.IOrderDetailService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailService implements IOrderDetailService {
    @Inject
    private OrderDetailDAO orderDetailDAO;

    @Inject
    private OrderDetailMapper orderDetailMapper;
    @Override
    public List<OrderDetailDTO> findAllByOrderId(Long orderId) {
        List<OrderDetailEntity> orderDetailEntities = orderDetailDAO.findAllByOrderId(orderId);
        return orderDetailMapper.toDTOList(orderDetailEntities);
    }

    @Override
    public List<DisplayOrderDetailDTO> showOrderDetail(Long orderId) {
        return  orderDetailDAO.showOrderDetail(orderId);
    }

    @Override
    public DisplayOrderDetailDTO findOrderDetail(Long orderDetailId) {
        return orderDetailDAO.findOrderDetail(orderDetailId);
    }
}
