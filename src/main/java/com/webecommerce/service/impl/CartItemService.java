package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.cart.CartDAO;
import com.webecommerce.dao.impl.people.CustomerDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.mapper.Impl.CartItemMapper;
import com.webecommerce.mapper.Impl.ProductVariantMapper;
import com.webecommerce.service.ICartItemService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemService implements ICartItemService {

    @Inject
    private IProductVariantDAO productVariantDAO;

    @Inject
    private ProductVariantMapper productVariantMapper;

    @Inject
    private CustomerDAO customerDAO;

    @Inject
    private CartItemMapper cartItemMapper;

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
    @Transactional
    public CartEntity editCart(Long idCustomer, HashMap<Long, CartItemDTO> cart) {
        // Tìm giỏ hàng hiện tại của khách hàng
        CustomerEntity customerEntity = customerDAO.findById(idCustomer);
        CartEntity cartEntity = customerEntity.getCart();

        if (cartEntity == null) {
            // Khách hàng mới chưa có giỏ hàng
            cartEntity = new CartEntity();
            cartEntity.setCartItems(new ArrayList<>());

            // Tạo liên kết
            cartEntity.setCustomer(customerEntity);
            customerEntity.setCart(cartEntity);
        }

        // Lấy danh sách các CartItem hiện có trong giỏ hàng
        List<CartItemEntity> existingCartItems = cartEntity.getCartItems();

        // Duyệt qua các sản phẩm trong giỏ hàng mới
        for (Map.Entry<Long, CartItemDTO> entry : cart.entrySet()) {
            Long productVariantId = entry.getKey();
            CartItemDTO cartItemDTO = entry.getValue();
            boolean itemExists = false;

            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng
            for (CartItemEntity existingItem : existingCartItems) {
                if (existingItem.getProductVariant().getId().equals(productVariantId)) {
                    existingItem.setQuantity(cartItemDTO.getQuantity());
                    itemExists = true;
                    break;
                }
            }

            // Nếu sản phẩm chưa tồn tại, thêm vào giỏ hàng
            if (!itemExists) {
                CartItemEntity newItem = cartItemMapper.toEntity(cartItemDTO);
                newItem.setCart(cartEntity);
                cartEntity.getCartItems().add(newItem);
            }
        }

        customerDAO.update(customerEntity);
        return cartEntity;
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