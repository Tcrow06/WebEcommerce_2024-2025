package com.webecommerce.dao.cart;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.cart.CartEntity;

public interface ICartDAO  extends GenericDAO <CartEntity> {
    // Lấy giỏ hàng qua id của khách hàng
    CartEntity findByCustomerId(Long customerId);
}
