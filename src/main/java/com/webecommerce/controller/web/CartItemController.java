package com.webecommerce.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.service.IBillDiscountService;
import com.webecommerce.service.ICartItemService;
import com.webecommerce.service.impl.CartItemService;
import com.webecommerce.service.impl.ProductVariantService;
import com.webecommerce.utils.HttpUtils;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;

@SuppressWarnings("ALL")
@WebServlet(urlPatterns = {"/gio-hang", "/them-gio-hang", "/sua-gio-hang", "/xoa-gio-hang"})
public class CartItemController extends HttpServlet {

    @Inject
    private ICartItemService cartItemService;

    @Inject
    private ProductVariantService productVariantService;

    @Inject
    private IBillDiscountService billDiscountService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = JWTUtil.getIdUser(request);
            List<BillDiscountDTO> billDiscountDTOS = billDiscountService.getAllDiscountEligible(id);
            request.setAttribute("discountList", billDiscountDTOS);
            String path = request.getServletPath();
            if (path.equals("/gio-hang")) {
                request.getRequestDispatcher("/views/web/cart.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Không thể thực hiện thao tác này.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        Long userId = JWTUtil.getIdUser(request);

        if (path.equals("/sua-gio-hang") || path.equals("/xoa-gio-hang")) {
            handleUpdateCart(request, response, userId);
        } else {
            handleAddCart(request, response, path, userId);
        }
    }

    private void handleUpdateCart(HttpServletRequest request, HttpServletResponse response, Long userId) throws IOException {
        HttpSession session = request.getSession();


        HashMap<Long, CartItemDTO> updatedCart;
        if(request.getServletPath().equals("/sua-gio-hang")){
            // Lấy dữ liệu JSON từ request
            String jsonData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> data = new Gson().fromJson(jsonData, type);

            List<Map<String, Object>> cartItems = (List<Map<String, Object>>) data.get("cartItems");
            HashMap<Long, CartItemDTO> newCart = new HashMap<>();
            for (Map<String, Object> item : cartItems) {
                Long productVariantId = ((Double) item.get("productVariantId")).longValue();
                int quantity = ((Double) item.get("quantity")).intValue();
                CartItemDTO cartItemDTO = new CartItemDTO();
                cartItemDTO.setProductVariant(productVariantService.findById(productVariantId));
                cartItemDTO.setQuantity(quantity);
                newCart.put(productVariantId, cartItemDTO);
            }
            updatedCart = cartItemService.updateCartItem(userId, newCart);
        }
        else {
            String json = HttpUtils.converter(request.getReader());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Long> productVariantIds = objectMapper.readValue(json, new TypeReference<List<Long>>() {});
            updatedCart = cartItemService.deleteCartItem(userId,productVariantIds);
        }

        session.setAttribute("cart", updatedCart);

        // Trả về JSON
        Map<String, Object> result = new HashMap<>();
        result.put("totalPrice", cartItemService.getPriceOfCart(updatedCart));
        result.put("totalQuantity", cartItemService.getQuantityOfCart(updatedCart));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(result));
    }

    private void handleAddCart(HttpServletRequest request, HttpServletResponse response, String path, Long userId) throws IOException {
        HttpSession session = request.getSession();

        Long productVariantId = Long.parseLong(request.getParameter("productVariantId"));
        HashMap<Long, CartItemDTO> cart = (HashMap<Long, CartItemDTO>) session.getAttribute("cart");
        String message="" ;
        String status="";
        if (cart == null) {
            cart = new HashMap<>();
        }
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if(quantity > 0) {
            if (JWTUtil.getIdUser(request) == null) {
                List<CartItemDTO> cartItemDTOS = new ArrayList<>(cart.values());
                cartItemDTOS = cartItemService.addCartAnonymous(cartItemDTOS, productVariantId, quantity);
                for (CartItemDTO item : cartItemDTOS) {
                    cart.put(item.getProductVariant().getId(), item); // Sử dụng productVariantId làm key
                }
            } else {
                cart = cartItemService.addCartItem(productVariantId, quantity, userId);
            }
        }
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("status",status);
        jsonResponse.put("message",message);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), jsonResponse);
        session.setAttribute("cart", cart);

        response.sendRedirect(request.getHeader("referer") != null ? request.getHeader("referer") : "/gio-hang");
    }

}
