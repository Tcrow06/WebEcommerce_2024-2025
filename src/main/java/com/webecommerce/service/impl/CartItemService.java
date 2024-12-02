package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.cart.ICartDAO;
import com.webecommerce.dao.cart.ICartItemDAO;
import com.webecommerce.dao.impl.cart.CartDAO;
import com.webecommerce.dao.impl.people.CustomerDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.PlacedOrder.ProductOrderDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.Impl.CartItemMapper;
import com.webecommerce.mapper.Impl.ProductVariantMapper;
import com.webecommerce.service.ICartItemService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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

    @Inject
    private ProductVariantMapper productVariantMapper;

    @Override
    public HashMap<Long, CartItemDTO> convertCartForSession(CartEntity cartEntity) {

        List<CartItemDTO> cartItems = cartEntity.getCartItems().stream()
                .map(cartItemMapper::toDTO).sorted((item1, item2) -> {
                    boolean isUnavailable1 = item1.getProductVariant().getStatus() == EnumProductStatus.STOP_SELLING;
                    boolean isUnavailable2 = item2.getProductVariant().getStatus() == EnumProductStatus.STOP_SELLING;
                    return Boolean.compare(isUnavailable1, isUnavailable2);
                }).toList();

        // Chuyển đổi các CartItemDTO đã sắp xếp thành HashMap (LinkedHashMap giữ thứ tự)
        HashMap<Long, CartItemDTO> cart = new LinkedHashMap<>();
        for (CartItemDTO item : cartItems) {
            cart.put(item.getProductVariant().getId(), item);
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
        if(cartEntity==null){
            cartEntity = new CartEntity();
            cartEntity.setCustomer(customerEntity);
            cartDAO.insert(cartEntity);
        }
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
        try {
            CartEntity cartEntity = customerDAO.findById(idUser).getCart();
//            HashMap<Long, CartItemDTO> cart = new HashMap<>();
            HashMap<Long, CartItemDTO> cart = convertCartForSession(cartEntity);


            for (ProductOrderDTO productOrderDTO : checkOutRequestDTO.getSelectedProductsId()) {
                cartEntity.getCartItems().stream()
                        .filter(cartItemEntity -> cartItemEntity.getProductVariant().getId().equals(productOrderDTO.getProductVariantId()))
                        .findFirst()
                        .ifPresentOrElse(cartItemEntity -> {
                            cartItemEntity.setQuantity(productOrderDTO.getQuantity());
                            cartItemDAO.update(cartItemEntity);
                            CartItemDTO updatedCartItemDTO = cartItemMapper.toDTO(cartItemEntity);
                            updatedCartItemDTO.setIsActive(1);
                            cart.replace(updatedCartItemDTO.getProductVariant().getId(),updatedCartItemDTO);
//                            cart.set(updatedCartItemDTO.getId(), updatedCartItemDTO);
                        }, () -> {
                            CartItemEntity cartItem = new CartItemEntity();
                            ProductVariantEntity productVariantEntity = productVariantDAO.findById(productOrderDTO.getProductVariantId());
                            cartItem.setQuantity(productOrderDTO.getQuantity());
                            cartItem.setProductVariant(productVariantEntity);
                            cartItemDAO.insert(cartItem);
                            CartItemDTO updatedCartItemDTO = cartItemMapper.toDTO(cartItem);
                            updatedCartItemDTO.setIsActive(1);
                            cart.put(updatedCartItemDTO.getProductVariant().getId(), updatedCartItemDTO);
                        });
            }
            HashMap<Long, CartItemDTO> sortedCart = new LinkedHashMap<>();
            cart.entrySet().stream()
                    .sorted((entry1, entry2) -> Integer.compare(entry2.getValue().getIsActive(), entry1.getValue().getIsActive()))
                    .forEachOrdered(entry -> sortedCart.put(entry.getKey(), entry.getValue()));

            return sortedCart;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<Long, CartItemDTO> LoadCart(Long idUser) {
        try {
            CartEntity cartEntity = customerDAO.findById(idUser).getCart();
            HashMap<Long, CartItemDTO> cart = new HashMap<>();
            for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
                CartItemDTO cartItemDTO = cartItemMapper.toDTO(cartItemEntity);
                cart.put(cartItemDTO.getId(), cartItemDTO);
            }
            return cart;
        }catch (Exception   e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<CartItemDTO> addCartAnonymous(List<CartItemDTO> cart, Long productVariantId, int quantity) {
        cart.stream()
                .filter(cartItem -> cartItem.getProductVariant().getId().equals(productVariantId))
                .findFirst()
                .ifPresentOrElse(cartItem -> {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    },() -> {
                    ProductVariantDTO productVariantDTO = productVariantMapper.toDTO(productVariantDAO.findById(productVariantId));
                        CartItemDTO cartItem = new CartItemDTO();
                        cartItem.setProductVariant(productVariantDTO);
                        cartItem.setQuantity(quantity);
                        cart.add(cartItem);
                });

        return cart;
    }

    @Transactional
    @Override
    public HashMap<Long, CartItemDTO> updateCartWhenLogin(HashMap<Long, CartItemDTO> cart,Long idUser) {
        CartEntity cartEntity = customerDAO.findById(idUser).getCart();
        if(cart == null){
            return convertCartForSession(cartEntity);
        }
        // List<CartItemEntity> result = new ArrayList<>();
        List<CartItemEntity> cartItemEntityList = cartEntity.getCartItems();
        for(CartItemDTO cartItemDTO : new ArrayList<>(cart.values())){
            cartItemEntityList.stream()
                    .filter(cartItemEntity -> cartItemEntity.getProductVariant().getId().equals(cartItemDTO.getProductVariant().getId()))
                    .findFirst()
                    .ifPresentOrElse(cartItemEntity -> {
                        cartItemEntity.setQuantity(cartItemEntity.getQuantity() + cartItemDTO.getQuantity());
                        cartItemDAO.update(cartItemEntity);
                    },()-> {
                        CartItemEntity cartItem = new CartItemEntity();
                        ProductVariantEntity productVariantEntity = productVariantDAO.findById(cartItemDTO.getProductVariant().getId());
                        cartItem.setProductVariant(productVariantEntity);
                        cartItem.setCart(cartEntity);
                        cartItem.setQuantity(cartItemDTO.getQuantity());
                        cartItemDAO.insert(cartItem);
                        cartItemEntityList.add(cartItem);
                    });
        }
        cartEntity.setCartItems(cartItemEntityList);
        return convertCartForSession(cartEntity);
    }

    @Transactional
    @Override
    public HashMap<Long, CartItemDTO> deleteCartItem(Long userId,List<Long> productVariantIds){
        CustomerEntity customerEntity = customerDAO.findById(userId);
        CartEntity cartEntity = customerEntity.getCart();
        List<CartItemEntity> list  = cartEntity.getCartItems();
        for(Long productVariantId : productVariantIds){
            list.stream()
                    .filter(cartItemEntity -> cartItemEntity.getProductVariant().getId().equals(productVariantId))
                    .findFirst()
                    .ifPresent(cartItemEntity -> {
                        cartItemDAO.delete(cartItemEntity.getId());
                        list.remove(cartItemEntity);
                    });
        }
        cartEntity.setCartItems(list);
        return convertCartForSession(cartEntity);
    }


}