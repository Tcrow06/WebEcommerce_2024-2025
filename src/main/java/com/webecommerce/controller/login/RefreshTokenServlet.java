package com.webecommerce.controller.login;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.webecommerce.constant.EnumRole;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.response.people.OwnerResponse;
import com.webecommerce.dto.response.people.UserResponse;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.mapper.Impl.CartItemMapper;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/refreshAuthToken"})
public class RefreshTokenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ICustomerDAO customerDAO;

    @Inject
    private CartItemMapper cartItemMapper;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldToken = request.getHeader("Authorization").replace("Bearer ", "");
        DecodedJWT decodedJWT = JWTUtil.verifyToken(oldToken);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        if (decodedJWT != null) {
            Long id = decodedJWT.getClaim("id").asLong();
            String role = decodedJWT.getClaim("role").asString();
            UserResponse userModel = new OwnerResponse();
            userModel.setId(id);
            userModel.setRole(EnumRole.valueOf(role));
            String token = JWTUtil.generateToken(userModel);
            Cookie tokenCookie = new Cookie("token", token);
//            tokenCookie.setPath("/");
//            tokenCookie.setHttpOnly(true);
            response.addCookie(tokenCookie);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("valid", true);
            responseData.put("token", token);
            response.getWriter().write(new Gson().toJson(responseData));
        } else {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("valid", false);
            response.getWriter().write(new Gson().toJson(responseData));
        }
    }

}
