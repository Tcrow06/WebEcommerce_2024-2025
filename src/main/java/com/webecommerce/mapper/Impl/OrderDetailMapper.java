package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailMapper implements GenericMapper<OrderDetailDTO, OrderDetailEntity> {
    @Inject
    private ProductDiscountMapper productDiscountMapper;
    @Inject
    private ProductVariantMapper productVariantMapper;

    @Override
    public OrderDetailDTO toDTO(OrderDetailEntity orderDetailEntity) {
        if(orderDetailEntity == null) return null;
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetailEntity.getId());
        orderDetailDTO.setQuantity(orderDetailEntity.getQuantity());
        orderDetailDTO.setProductDiscount(productDiscountMapper.toDTO(orderDetailEntity.getProductDiscount()));
        return orderDetailDTO;
    }


    @Override
    public OrderDetailEntity toEntity(OrderDetailDTO orderDetailDTO) {
        if(orderDetailDTO == null) return null;
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setId(orderDetailDTO.getId());
        orderDetailEntity.setQuantity(orderDetailDTO.getQuantity());
        orderDetailEntity.setProductDiscount(productDiscountMapper.toEntity(orderDetailDTO.getProductDiscount()));
        return orderDetailEntity;
    }

    @Override
    public List<OrderDetailDTO> toDTOList(List<OrderDetailEntity> orderDetailEntities) {
//        return GenericMapper.super.toDTOList(orderDetailEntities);
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetailEntity entity : orderDetailEntities) {
            OrderDetailDTO orderDetailDTO = toDTO(entity);
            orderDetailDTOS.add(orderDetailDTO);
        }
        return orderDetailDTOS;
    }

    @Override
    public List<OrderDetailEntity> toEntityList(List<OrderDetailDTO> orderDetailDTOS) {
        return GenericMapper.super.toEntityList(orderDetailDTOS);
    }
}
