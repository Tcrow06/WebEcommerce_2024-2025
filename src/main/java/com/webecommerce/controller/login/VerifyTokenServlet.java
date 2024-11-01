package com.webecommerce.controller.login;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.webecommerce.utils.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/verifyToken")
public class VerifyTokenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        DecodedJWT jwt = JWTUtil.verifyToken(token);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Map<String, Object> responseData = new HashMap<>();
        if (jwt != null) {
            responseData.put("valid", true);
            responseData.put("exp", jwt.getExpiresAt().getTime() / 1000);
        } else {
            responseData.put("valid", false);
        }
        response.getWriter().write(new Gson().toJson(responseData));
    }
}

