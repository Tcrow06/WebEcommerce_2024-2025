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
    public OrderDetailDTO toDTO(OrderDetailEntity entity) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(entity.getId());
        orderDetailDTO.setQuantity(entity.getQuantity());
        orderDetailDTO.setProductDiscount(productDiscountMapper.toDTO(entity.getProductDiscount()));
        orderDetailDTO.setProductVariant(productVariantMapper.toDTO(entity.getProductVariant()));
        return orderDetailDTO;
    }

    @Override
    public OrderDetailEntity toEntity(OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public List<OrderDetailDTO> toDTOList(List<OrderDetailEntity> orderDetailEntities) {
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
