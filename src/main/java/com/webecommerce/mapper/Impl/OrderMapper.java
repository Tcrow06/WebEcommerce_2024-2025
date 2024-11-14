package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
import java.util.List;

public class OrderMapper implements GenericMapper<OrderDTO, OrderEntity> {

    @Inject
    private OrderInfoMapper orderInfoMapper;
    @Inject
    private OrderDetailMapper orderDetailMapper;
    @Inject
    private OrderStatusMapper orderStatusMapper;
    @Override
    public OrderDTO toDTO(OrderEntity orderEntity) {
        OrderDTO dto = new OrderDTO();
        dto.setId(orderEntity.getId());
        dto.setOrderInfoDTO(orderInfoMapper.toDTO(orderEntity.getOrderInfo()));
        dto.setOrderDetails(orderDetailMapper.toDTOList(orderEntity.getOrderDetails()));
        dto.setOrderStatuses(orderStatusMapper.toDTOList(orderEntity.getOrderStatuses()));
        dto.setBillDiscount(orderEntity.getBillDiscount());
        dto.setShippingFee(orderEntity.getShippingFee());
        return dto;
    }

    @Override
    public OrderEntity toEntity(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> toDTOList(List<OrderEntity> orderEntities) {
        return GenericMapper.super.toDTOList(orderEntities);
    }

    @Override
    public List<OrderEntity> toEntityList(List<OrderDTO> orderDTOS) {
        return GenericMapper.super.toEntityList(orderDTOS);
    }
}
