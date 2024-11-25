package com.webecommerce.service;

import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.entity.cart.CartEntity;

import java.util.HashMap;
import java.util.List;

public interface ICartItemService {

    HashMap<Long, CartItemDTO> addCartItem(Long productVariantId, int quantity, Long userId);

    HashMap<Long, CartItemDTO> updateCartItem(Long userId, HashMap<Long, CartItemDTO> newCart);

    int getQuantityOfCart(HashMap<Long, CartItemDTO> cart);

    double getPriceOfCart(HashMap<Long, CartItemDTO> cart);

    HashMap<Long,CartItemDTO> updateCartWhenBuy(Long idUser, CheckOutRequestDTO checkOutRequestDTO);

    HashMap<Long, CartItemDTO> LoadCart(Long idUser);

    HashMap<Long, CartItemDTO> convertCartForSession(CartEntity cartEntity);

    List<CartItemDTO> addCartAnonymous(List<CartItemDTO> cart, Long productVariantId, int quantity);

    HashMap<Long, CartItemDTO> updateCartWhenLogin(HashMap<Long, CartItemDTO> cart,Long idUser);

    HashMap<Long, CartItemDTO> deleteCartItem(Long userId, List<Long> productVariantIds);

}
