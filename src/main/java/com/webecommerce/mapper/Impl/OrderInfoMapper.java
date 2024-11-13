package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
import java.util.List;

public class OrderInfoMapper implements GenericMapper<OrderInfoDTO, OrderInfoEntity> {
    @Inject
    private AddressMapper addressMapper;
    @Override
    public OrderInfoDTO toDTO(OrderInfoEntity entity) {
        OrderInfoDTO dto = new OrderInfoDTO();
        dto.setId(entity.getId());
        dto.setAddress(addressMapper.toDTO(entity.getAddress()));
        dto.setPhone(entity.getPhone());
        dto.setRecipient(entity.getRecipient());
        return dto;
    }

    @Override
    public OrderInfoEntity toEntity(OrderInfoDTO orderInfoDTO) {
        return null;
    }

    @Override
    public List<OrderInfoDTO> toDTOList(List<OrderInfoEntity> orderInfoEntities) {
        return GenericMapper.super.toDTOList(orderInfoEntities);
    }

    @Override
    public List<OrderInfoEntity> toEntityList(List<OrderInfoDTO> orderInfoDTOS) {
        return GenericMapper.super.toEntityList(orderInfoDTOS);
    }
}
