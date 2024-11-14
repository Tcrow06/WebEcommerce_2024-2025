package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.mapper.GenericMapper;

import java.util.List;

public class OrderStatusMapper implements GenericMapper<OrderStatusDTO, OrderStatusEntity> {

    @Override
    public OrderStatusDTO toDTO(OrderStatusEntity orderStatusEntity) {
        return null;
    }

    @Override
    public OrderStatusEntity toEntity(OrderStatusDTO orderStatusDTO) {
        return null;
    }

    @Override
    public List<OrderStatusDTO> toDTOList(List<OrderStatusEntity> orderStatusEntities) {
        return GenericMapper.super.toDTOList(orderStatusEntities);
    }

    @Override
    public List<OrderStatusEntity> toEntityList(List<OrderStatusDTO> orderStatusDTOS) {
        return GenericMapper.super.toEntityList(orderStatusDTOS);
    }
}
