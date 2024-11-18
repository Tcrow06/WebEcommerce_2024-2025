package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.service.IOrderInfoService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-them-thong-tin-giao-hang", "/api-dat-dia-chi-mac-dinh",
        "/api-xoa-thong-tin-giao-hang", "/api-cap-nhat-thong-tin-giao-hang"})
public class OrderInfoAPI extends HttpServlet {

    @Inject
    private IOrderInfoService orderInfoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String action = request.getServletPath();
        switch (action) {
            case "/api-them-thong-tin-giao-hang" -> handleAdd(request, response);
            case "/api-xoa-thong-tin-giao-hang" -> handleDelete(request, response);
            case "/api-cap-nhat-thong-tin-giao-hang" -> handleUpdate(request, response);
            case "/api-dat-dia-chi-mac-dinh" -> handleSetDefault(request, response);
        }
    }

    private void handleAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        OrderInfoDTO orderInfo = objectMapper.readValue(jsonString.toString(), OrderInfoDTO.class);

        OrderInfoDTO result = orderInfoService.addOrderInfo(orderInfo);

        if (result != null || orderInfoService.deleteOrderInfo(orderInfo.getId())) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\": true}");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false}");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder jsonString = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Long orderInfoId = objectMapper.readTree(jsonString.toString()).get("id").asLong();

        var isDeleted = orderInfoService.deleteOrderInfo(orderInfoId);
        if (isDeleted) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\": true}");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false}");
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    private void handleSetDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Long orderInfoId = objectMapper.readTree(jsonString.toString()).get("id").asLong();
        var result = orderInfoService.setOrderInfoDefault(orderInfoService.getOrderInfoById(orderInfoId));

        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\": true}");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false}");
        }
    }
}

