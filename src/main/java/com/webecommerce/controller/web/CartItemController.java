package com.webecommerce.controller.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.service.impl.CartItemService;
import com.webecommerce.service.impl.ProductVariantService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/gio-hang", "/them-gio-hang", "/sua-gio-hang", "/xoa-gio-hang"})
public class CartItemController extends HttpServlet {

    @Inject
    private CartItemService cartItemService;

    @Inject
    private ProductVariantService productVariantService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/gio-hang")) {
            request.getRequestDispatcher("/views/web/cart.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Không thể thực hiện thao tác này.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/sua-gio-hang")) {
            handleUpdateCart(request, response);
        } else {
            handleCart(request, response, path);
        }
    }

    private void handleUpdateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy dữ liệu JSON từ request
        String jsonData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        // Parse JSON thành Map
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> data = new Gson().fromJson(jsonData, type);

        Long userId = JWTUtil.getIdUser(request);
        List<Map<String, Object>> cartItems = (List<Map<String, Object>>) data.get("cartItems");

        // Tạo HashMap để lưu các sản phẩm cập nhật
        HashMap<Long, CartItemDTO> cart = new HashMap<>();
        for (Map<String, Object> item : cartItems) {
            Long productVariantId = ((Double) item.get("productVariantId")).longValue();
            int quantity = ((Double) item.get("quantity")).intValue();

            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setProductVariant(productVariantService.findById(productVariantId));
            cartItemDTO.setQuantity(quantity);

            cart.put(productVariantId, cartItemDTO);
        }

        // Tiến hành update
        cartItemService.editCart(null, 0, cart);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Map.of("message", "Giỏ hàng đã được cập nhật thành công")));
    }

    private void handleCart(HttpServletRequest request, HttpServletResponse response, String path)
            throws IOException {
        // Trả về JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        Long productVariantId = Long.parseLong(request.getParameter("productVariantId"));
        int quantity = 0;
        if (path.equals("/them-gio-hang") || path.equals("/sua-gio-hang")) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }

        HashMap<Long, CartItemDTO> cart = (HashMap<Long, CartItemDTO>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        switch (path) {
            case "/them-gio-hang" -> cart = cartItemService.addCart(productVariantId, quantity, cart);
            case "/xoa-gio-hang" -> cart = cartItemService.deleteCart(productVariantId, cart);
            default ->
                    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Không thể thực hiện thao tác này.");
        }

        session.setAttribute("cart", cart);
        session.setAttribute("totalPrice", cartItemService.getPriceOfCart(cart));
        session.setAttribute("totalQuantity", cartItemService.getQuantityOfCart(cart));

        String referer = request.getHeader("referer");
        response.sendRedirect(referer != null ? referer : "/gio-hang");

        // Dữ liệu JSON trả về
        Map<String, Object> result = new HashMap<>();
        result.put("totalPrice", session.getAttribute("totalPrice"));
        result.put("totalQuantity", session.getAttribute("totalQuantity"));
        response.getWriter().write(new Gson().toJson(result));
    }

    private Long extractIdFromPath(String pathInfo) {
        String[] pathParts = pathInfo.split("/");
        return Long.parseLong(pathParts[1]);
    }
}
