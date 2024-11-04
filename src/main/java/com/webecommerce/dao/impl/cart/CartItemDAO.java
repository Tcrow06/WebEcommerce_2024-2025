package com.webecommerce.dao.impl.cart;


import com.webecommerce.dao.cart.ICartItemDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.cart.CartItemEntity;

public class CartItemDAO extends AbstractDAO<CartItemEntity> implements ICartItemDAO {

    public CartItemDAO() {
        super(CartItemEntity.class);
    }
}
