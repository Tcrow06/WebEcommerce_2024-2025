package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.mapper.GenericMapper;

public class CartItemMapper implements GenericMapper<CartItemDTO, CartItemEntity> {


    @Override
    public CartItemDTO toDTO(CartItemEntity cartItemEntity) {
        return null;
    }

    @Override
    public CartItemEntity toEntity(CartItemDTO cartItemDTO) {
        return null;
    }
}
