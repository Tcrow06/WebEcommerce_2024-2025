package com.webecommerce.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.service.IOrderService;
import com.webecommerce.utils.HttpUtils;
import com.webecommerce.utils.JWTUtil;
import com.webecommerce.utils.VNPayUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = "/thanh-toan-vnpay")
public class VNPayController extends HttpServlet {

    @Inject
    private IOrderService orderService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Nhận dữ liệu từ request
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        OrderDTO orderDTO =  HttpUtils.of(request.getReader()).toModel(OrderDTO.class);
        Map<String, Object> jsonResponse = new HashMap<>();
        var resultOrderDTO = orderService.findInfoPayment(orderDTO, JWTUtil.getIdUser(request));


        if (resultOrderDTO.getStatus().contains("error")) {
            jsonResponse.put("redirectUrl", "/thanh-toan");
            jsonResponse.put("message",resultOrderDTO.getMessage().toString());
            jsonResponse.put("status","error");
        } else if (resultOrderDTO.getStatus().contains("warning")) {
            jsonResponse.put("message",resultOrderDTO.getMessage().toString());
            jsonResponse.put("status","warning");
        } else {
            jsonResponse.put("redirectUrl", VNPayUtils.transaction(request, orderDTO.getTotal()));
            jsonResponse.put("message",resultOrderDTO.getMessage().toString());
            jsonResponse.put("status","success");
        }
        objectMapper.writeValue(response.getWriter(), jsonResponse);
    }
}
