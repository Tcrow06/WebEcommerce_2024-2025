package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.AddressDTO;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;

public class OrderInfoMapper implements GenericMapper<OrderInfoDTO, OrderInfoEntity> {
    @Inject
    private AddressMapper addressMapper;
    @Override
    public OrderInfoDTO toDTO(OrderInfoEntity orderInfoEntity) {
        if(orderInfoEntity == null)
            return null;
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setAddress(addressMapper.toDTO(orderInfoEntity.getAddress()));
        orderInfoDTO.setId(orderInfoEntity.getId());
        orderInfoDTO.setRecipient(orderInfoEntity.getRecipient());
        orderInfoDTO.setPhone(orderInfoEntity.getPhone());
        return orderInfoDTO;
    }

    @Override
    public OrderInfoEntity toEntity(OrderInfoDTO orderInfoDTO) {
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        orderInfoEntity.setId(orderInfoDTO.getId());
        orderInfoEntity.setRecipient(orderInfoDTO.getRecipient());
        orderInfoEntity.setPhone(orderInfoDTO.getPhone());
        orderInfoEntity.setAddress(addressMapper.toEntity(orderInfoDTO.getAddress()));
        return orderInfoEntity;
    }
}
