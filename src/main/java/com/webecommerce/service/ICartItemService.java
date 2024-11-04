package com.webecommerce.service;

import com.webecommerce.dto.CartItemDTO;

import java.util.HashMap;

public interface ICartItemService {

    HashMap<Long, CartItemDTO> addCart(Long id, HashMap<Long, CartItemDTO> cart);

    HashMap<Long, CartItemDTO> editCart(Long id, int quantity, HashMap<Long, CartItemDTO> cart);

    HashMap<Long, CartItemDTO> deleteCart(Long id, HashMap<Long, CartItemDTO> cart);

    public int getQuantityOfCart(HashMap<Long, CartItemDTO> cart);

    public double getPriceOfCart(HashMap<Long, CartItemDTO> cart);
}
