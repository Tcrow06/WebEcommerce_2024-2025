package com.webecommerce.service.impl;

import com.webecommerce.dao.cart.ICartDAO;
import com.webecommerce.dao.cart.ICartItemDAO;
import com.webecommerce.dao.impl.cart.CartDAO;
import com.webecommerce.dao.impl.people.CustomerDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.PlacedOrder.ProductOrderDTO;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.Impl.CartItemMapper;
import com.webecommerce.service.ICartItemService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

public class CartItemService implements ICartItemService {

    @Inject
    private IProductVariantDAO productVariantDAO;

    @Inject
    private CustomerDAO customerDAO;

    @Inject
    private CartItemMapper cartItemMapper;

    @Inject
    private ICartItemDAO cartItemDAO;

    @Inject
    private ICartDAO cartDAO;

    @Override
    public HashMap<Long, CartItemDTO> convertCartForSession(CartEntity cartEntity) {
        HashMap<Long, CartItemDTO> cart = new HashMap<>();
        for (CartItemEntity item : cartEntity.getCartItems()) {
            CartItemDTO cartItemDTO = cartItemMapper.toDTO(item);
            cart.put(item.getProductVariant().getId(), cartItemDTO);
        }
        return cart;
    }

    @Override
    @Transactional
    public HashMap<Long, CartItemDTO> addCartItem(Long productVariantId, int quantity, Long userId) {
        // Lấy thông tin sản phẩm từ database
        ProductVariantEntity productVariantEntity = productVariantDAO.findById(productVariantId);

        // Lấy thông tin khách hàng từ database
        CustomerEntity customerEntity = customerDAO.findById(userId);
        CartEntity cartEntity = customerEntity.getCart();
        List<CartItemEntity> cartItemEntities = cartEntity.getCartItems();

        // Tạo giỏ hàng mới nếu chưa có

        // Kiểm tra sản phẩm đã có trong giỏ hàng hay chưa
        boolean itemExists = false;
        for (CartItemEntity existingItem : cartItemEntities) {
            if (existingItem.getProductVariant().getId().equals(productVariantId)) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                cartItemDAO.update(existingItem);
                itemExists = true;
                break;
            }
        }

        // Thêm sản phẩm mới vào giỏ hàng
        if (!itemExists) {
            CartItemEntity newItem = new CartItemEntity();
            newItem.setProductVariant(productVariantEntity);
            newItem.setQuantity(quantity);
            newItem.setCart(cartEntity);

            cartEntity.getCartItems().add(newItem);
            cartItemDAO.insert(newItem);

        }


        // Cập nhật cart vào session
        return convertCartForSession(cartEntity);
    }

    @Override
    public HashMap<Long, CartItemDTO> updateCartItem(Long userId, HashMap<Long, CartItemDTO> newCart) {
        // Lấy thông tin khách hàng và giỏ hàng từ bảng Customer
        CustomerEntity customerEntity = customerDAO.findById(userId);
        CartEntity cartEntity = customerEntity.getCart();

        // Duyệt qua các mục mới và cập nhật hoặc thêm vào giỏ hàng
        for (Map.Entry<Long, CartItemDTO> entry : newCart.entrySet()) {
            Long productVariantId = entry.getKey();
            CartItemDTO cartItemDTO = entry.getValue();

            for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
                if (cartItemEntity.getProductVariant().getId().equals(productVariantId)) {
                    cartItemEntity.setQuantity(cartItemDTO.getQuantity());
                }
            }
        }

        // Xóa các sản phẩm được yêu cầu
//        Iterator<CartItemEntity> iterator = cartEntity.getCartItems().iterator();
//        while (iterator.hasNext()) {
//            CartItemEntity item = iterator.next();
//            if (newCart.containsKey(item.getProductVariant().getId())) {
//                iterator.remove();
//                cartItemDAO.delete(item.getId());
//            }
//        }

        // Lưu giỏ hàng vào database
        customerDAO.update(customerEntity);

        // Cập nhật giỏ hàng vào session
        return convertCartForSession(cartEntity);
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

    @Override
    public HashMap<Long, CartItemDTO> updateCartWhenBuy(Long idUser, CheckOutRequestDTO checkOutRequestDTO) {
        CartEntity cartEntity = customerDAO.findById(idUser).getCart();
        HashMap<Long, CartItemDTO> cart = new HashMap<>();

        for (ProductOrderDTO productOrderDTO : checkOutRequestDTO.getSelectedProductsId()) {
            cartEntity.getCartItems().stream()
                    .filter(cartItemEntity -> cartItemEntity.getProductVariant().getId().equals(productOrderDTO.getProductVariantId()))
                    .findFirst()
                    .ifPresentOrElse(cartItemEntity -> {
                        cartItemEntity.setQuantity(productOrderDTO.getQuantity());
                        cartItemDAO.update(cartItemEntity);
                        CartItemDTO updatedCartItemDTO = cartItemMapper.toDTO(cartItemEntity);
                        updatedCartItemDTO.setIsActive(1);
                        cart.put(updatedCartItemDTO.getId(), updatedCartItemDTO);
                    }, () -> {
                        CartItemEntity cartItem = new CartItemEntity();
                        ProductVariantEntity productVariantEntity = productVariantDAO.findById(productOrderDTO.getProductVariantId());
                        cartItem.setQuantity(productOrderDTO.getQuantity());
                        cartItem.setProductVariant(productVariantEntity);
                        cartItemDAO.insert(cartItem);
                        CartItemDTO updatedCartItemDTO = cartItemMapper.toDTO(cartItem);
                        updatedCartItemDTO.setIsActive(1);
                        cart.put(updatedCartItemDTO.getId(), updatedCartItemDTO);
                    });
        }
        LinkedHashMap<Long, CartItemDTO> sortedCart = new LinkedHashMap<>();
        cart.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue().getIsActive(), entry1.getValue().getIsActive()))
                .forEachOrdered(entry -> sortedCart.put(entry.getKey(), entry.getValue()));

        return sortedCart;
    }

    @Override
    @Transactional
    public HashMap<Long, CartItemDTO> LoadCart(Long idUser) {
        CartEntity cartEntity = customerDAO.findById(idUser).getCart();
        HashMap<Long, CartItemDTO> cart = new HashMap<>();
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            CartItemDTO cartItemDTO = cartItemMapper.toDTO(cartItemEntity);
            cartItemDTO.setIsActive(1);
            cart.put(cartItemDTO.getId(), cartItemDTO);
        }
        return cart;
    }



}