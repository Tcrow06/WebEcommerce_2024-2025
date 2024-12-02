package com.webecommerce.mapper.Impl;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.impl.cart.CartDAO;
import com.webecommerce.dao.impl.product.ProductVariantDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;

public class CartItemMapper implements GenericMapper<CartItemDTO, CartItemEntity> {

    @Inject
    private CartDAO cartDAO;

    @Inject
    private ProductVariantDAO productVariantDAO;

    @Inject
    private ProductVariantMapper productVariantMapper;

    @Override
    public CartItemDTO toDTO(CartItemEntity cartItemEntity) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItemEntity.getId());
        cartItemDTO.setQuantity(cartItemEntity.getQuantity());
        cartItemDTO.setProductVariant(productVariantMapper.toDTO(cartItemEntity.getProductVariant()));

        // lấy status product variant chính xác
        if (cartItemDTO.getProductVariant() != null) {
            cartItemDTO.getProductVariant().setStatus(
                    (cartItemEntity.getProductVariant().getProduct().getStatus() == EnumProductStatus.SELLING &&
                            cartItemEntity.getProductVariant().getStatus() == EnumProductStatus.SELLING)
                            ? EnumProductStatus.SELLING
                            : EnumProductStatus.STOP_SELLING
            );
        }

        // Cập nhật lại mức giá mới (Do giá có thể thay đổi trong quá trình khách hàng lưu hàng trong giỏ)
        cartItemDTO.setTotalPrice(cartItemEntity.getQuantity() * cartItemEntity.getProductVariant().getPrice());
        return cartItemDTO;
    }

    @Override
    public CartItemEntity toEntity(CartItemDTO cartItemDTO) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setId(cartItemDTO.getId());
        cartItemEntity.setQuantity(cartItemDTO.getQuantity());
        cartItemEntity.setCart(cartDAO.findById(cartItemDTO.getCartId()));
        cartItemEntity.setProductVariant(productVariantDAO.findById(cartItemDTO.getProductVariant().getId()));
        return cartItemEntity;
    }
}
