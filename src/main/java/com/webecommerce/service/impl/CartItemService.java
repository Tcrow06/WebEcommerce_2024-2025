package com.webecommerce.service.impl;

import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.mapper.Impl.ProductVariantMapper;
import com.webecommerce.service.ICartItemService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class CartItemService implements ICartItemService {

    @Inject
    private IProductVariantDAO productVariantDAO;

    @Inject
    private ProductVariantMapper productVariantMapper;

    @Override
    public HashMap<Long, CartItemDTO> addCart(Long id, int quantity, HashMap<Long, CartItemDTO> cart) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        ProductVariantDTO productVariantDTO = productVariantMapper.toDTO(productVariantDAO.findById(id));

        if(productVariantDTO != null && cart.containsKey(id)) {
            cartItemDTO = cart.get(id);
            cartItemDTO.setQuantity(cartItemDTO.getQuantity() + quantity);
            cartItemDTO.setTotalPrice(cartItemDTO.getQuantity() * productVariantDTO.getPrice());
        } else {
            assert productVariantDTO != null;
            cartItemDTO.setProductVariant(productVariantDTO);
            cartItemDTO.setQuantity(quantity);
            cartItemDTO.setTotalPrice(productVariantDTO.getPrice() * quantity);
        }
        cart.put(id, cartItemDTO);
        return cart;
    }

    @Override
    public HashMap<Long, CartItemDTO> editCart(Long id, int quantity, HashMap<Long, CartItemDTO> cart) {

        ProductVariantDTO productVariantDTO = productVariantMapper.toDTO(productVariantDAO.findById(id));
        CartItemDTO cartItemDTO = new CartItemDTO();
        if (cart.containsKey(id)) {
            cartItemDTO = cart.get(id);
            cartItemDTO.setQuantity(quantity);
            cartItemDTO.setTotalPrice(productVariantDTO.getPrice() * quantity);
        }
        cart.put(id, cartItemDTO);
        return cart;
    }

    @Override
    public HashMap<Long, CartItemDTO> deleteCart(Long id, HashMap<Long, CartItemDTO> cart) {
        if (cart.containsKey(id)) {
            cart.remove(id);
        }
        return cart;
    }

    @Override
    public int getQuantityOfCart(HashMap<Long, CartItemDTO> cart) {
        int totalQuantity = 0;
        for(Map.Entry<Long, CartItemDTO> itemCart : cart.entrySet()) {
            totalQuantity += itemCart.getValue().getQuantity();
        }
        return totalQuantity;
    }

    @Override
    public double getPriceOfCart(HashMap<Long, CartItemDTO> cart) {
        double totalPrice = 0;
        for(Map.Entry<Long, CartItemDTO> itemCart : cart.entrySet()) {
            totalPrice += itemCart.getValue().getTotalPrice();
        }
        return totalPrice;
    }
}