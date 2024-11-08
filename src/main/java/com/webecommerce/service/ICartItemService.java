package com.webecommerce.service;

import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.entity.cart.CartEntity;

import java.util.HashMap;

public interface ICartItemService {

    HashMap<Long, CartItemDTO> addCart(Long id, int quantity, HashMap<Long, CartItemDTO> cart);

    CartEntity editCart(Long id, HashMap<Long, CartItemDTO> cart);

    HashMap<Long, CartItemDTO> deleteCart(Long id, HashMap<Long, CartItemDTO> cart);

    int getQuantityOfCart(HashMap<Long, CartItemDTO> cart);

    double getPriceOfCart(HashMap<Long, CartItemDTO> cart);
}
