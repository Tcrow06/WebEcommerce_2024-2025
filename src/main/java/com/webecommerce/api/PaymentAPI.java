package com.webecommerce.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.service.IPaymentService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-kiem-tra-thanh-toan"})
public class PaymentAPI extends HttpServlet {

    @Inject
    private IPaymentService paymentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Chuyển đối Json thành một map
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Map<String, Object> payload = objectMapper.readValue(json.toString(), new TypeReference<Map<String, Object>>() {});

        // Từ payload đã lấy, transfer data thành những thứ cần thiết
        List<Map<String, String>> dataFiller = (List<Map<String, String>>) payload.get("dataFiller");
        Map<String, Object> orderMap = (Map<String, Object>) payload.get("order");
        OrderDTO orderDTO = objectMapper.convertValue(orderMap, OrderDTO.class);

        var result = paymentService.checkPayment(dataFiller, orderDTO, JWTUtil.getIdUser(request));

        if (result) {
            response.getWriter().write("{\"status\":\"success\"}");
        } else {
            response.getWriter().write("{\"status\":\"fail\"}");
        }
    }
}
