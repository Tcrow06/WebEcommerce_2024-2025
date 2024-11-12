package com.webecommerce.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.PlacedOrder.ProductOrderDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;

@WebServlet(urlPatterns = {"/check-out"})
public class PlacedOrderController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String selectedProductsJson = request.getParameter("listSelectedProductsId");
        String billDiscountCode = request.getParameter("billDiscountCode");

        // Parse JSON thành danh sách sản phẩm
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductOrderDTO> listProduct = objectMapper.readValue(selectedProductsJson, new TypeReference<List<ProductOrderDTO>>() {});
        CheckOutRequestDTO checkOutRequestDTO = new CheckOutRequestDTO(listProduct,billDiscountCode);


        // Đặt danh sách sản phẩm và mã giảm giá vào request attribute để truyền tới JSP
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("discountCode", billDiscountCode);

        // Chuyển tiếp tới payment.jsp
        request.getRequestDispatcher("/views/web/payment.jsp").forward(request, response);
    }
}
