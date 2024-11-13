package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.mapper.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusMapper implements GenericMapper<OrderStatusDTO, OrderStatusEntity> {

    @Override
    public OrderStatusDTO toDTO(OrderStatusEntity entity) {
        OrderStatusDTO dto = new OrderStatusDTO();
        dto.setStatus(entity.getStatus());
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        return dto;
    }

    @Override
    public OrderStatusEntity toEntity(OrderStatusDTO orderStatusDTO) {
        return null;
    }

    @Override
    public List<OrderStatusDTO> toDTOList(List<OrderStatusEntity> orderStatusEntities) {
        List<OrderStatusDTO> dtos = new ArrayList<>();
        for(OrderStatusEntity orderStatusEntity : orderStatusEntities) {
            dtos.add(toDTO(orderStatusEntity));
        }
        return dtos;
    }

    @Override
    public List<OrderStatusEntity> toEntityList(List<OrderStatusDTO> orderStatusDTOS) {
        return GenericMapper.super.toEntityList(orderStatusDTOS);
    }
}
