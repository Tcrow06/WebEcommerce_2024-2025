package com.webecommerce.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.PlacedOrder.ProductOrderDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;


@WebServlet(urlPatterns = {"/thanh-toan"})
public class PaymentController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String state = request.getParameter("state");

        // Giải mã dữ liệu
        ObjectMapper objectMapper = new ObjectMapper();
        if (state != null && !state.isEmpty()) {
            String decodedState = URLDecoder.decode(state, StandardCharsets.UTF_8);
            CheckOutRequestDTO checkOutRequest = objectMapper.readValue(decodedState, CheckOutRequestDTO.class);

            List<ProductOrderDTO> listProduct = checkOutRequest.getSelectedProductsId();
            String billDiscountCode = checkOutRequest.getBillDiscountCode();

            // Lưu dữ liệu vào request để gửi tới JSP
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("billDiscountCode", billDiscountCode);
            request.getRequestDispatcher("/views/web/payment.jsp").forward(request, response);
        } else {
            response.sendRedirect("/error"); // Xử lý khi không có dữ liệu
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        CheckOutRequestDTO checkOutRequest = objectMapper.readValue(request.getReader(), CheckOutRequestDTO.class);
        Map<String, Object> result = new HashMap<>();

        List<ProductOrderDTO> listProduct = checkOutRequest.getSelectedProductsId();
        String billDiscountCode = checkOutRequest.getBillDiscountCode();
//        request.getRequestDispatcher("/views/web/payment.jsp").forward(request,response);

        request.setAttribute("listProductId", listProduct);
        request.setAttribute("discountCode", billDiscountCode);
        result.put("status","success");
        objectMapper.writeValue(response.getOutputStream(), result);
//        request.getRequestDispatcher("/views/web/payment.jsp").forward(request,response);

//        response.setContentType("application/json");
//        response.getWriter().write("{\"redirectUrl\": \"/xac-nhan-thanh-toan\"}");
    }
}
