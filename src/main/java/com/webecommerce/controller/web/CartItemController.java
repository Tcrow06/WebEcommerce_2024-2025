package com.webecommerce.controller.web;

import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.service.impl.CartItemService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/gio-hang", "/them-gio-hang", "/sua-gio-hang", "/xoa-gio-hang"})
public class CartItemController extends HttpServlet {

    @Inject
    private CartItemService cartItemService;

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
        handleCart(request, response, path);
    }

    private void handleCart(HttpServletRequest request, HttpServletResponse response, String path)
            throws IOException {
        HttpSession session = request.getSession();

        Long productVariantId = Long.parseLong(request.getParameter("productVariantId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HashMap<Long, CartItemDTO> cart = (HashMap<Long, CartItemDTO>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        switch (path) {
            case "/them-gio-hang" -> cart = cartItemService.addCart(productVariantId, quantity, cart);
            case "/sua-gio-hang" -> cart = cartItemService.editCart(productVariantId, quantity, cart);
            case "/xoa-gio-hang" -> cart = cartItemService.deleteCart(productVariantId, cart);
            default ->
                    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Không thể thực hiện thao tác này.");
        }

        session.setAttribute("cart", cart);
        session.setAttribute("totalPrice", cartItemService.getPriceOfCart(cart));
        session.setAttribute("totalQuantity", cartItemService.getQuantityOfCart(cart));

        String referer = request.getHeader("referer");
        response.sendRedirect(referer != null ? referer : "/gio-hang");
    }

    private Long extractIdFromPath(String pathInfo) {
        String[] pathParts = pathInfo.split("/");
        return Long.parseLong(pathParts[1]);
    }
}
