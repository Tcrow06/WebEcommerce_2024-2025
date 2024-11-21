package com.webecommerce.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webecommerce.service.IPaymentService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
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
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Chuyển đổi JSON thành List<Map<String, String>> bằng Gson
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, String>>>() {}.getType();
        List<Map<String, String>> dataFilter = gson.fromJson(json.toString(), listType);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Gọi logic nghiệp vụ đã xử lý để kiểm tra
        var result = paymentService.checkPayment(dataFilter);
        if (result) {
            response.getWriter().write("{\"status\":\"success\"}");
        } else {
            response.getWriter().write("{\"status\":\"fail\"}");
        }
    }
}
