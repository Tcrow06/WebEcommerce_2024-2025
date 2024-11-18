package com.webecommerce.dao.cart;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.cart.CartItemEntity;

public interface ICartItemDAO extends GenericDAO<CartItemEntity> {
    void deleteByProductVariantId(Long productVariantId);
}
