package com.webecommerce.dao.impl.cart;

import com.webecommerce.dao.cart.ICartDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.cart.CartEntity;

public class CartDAO extends AbstractDAO<CartEntity> implements ICartDAO {
    public CartDAO() {
        super(CartEntity.class);
    }
}
