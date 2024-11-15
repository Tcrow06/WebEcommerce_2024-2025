package com.webecommerce.controller.web;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.response.people.CustomerResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/trang-chu/don-hang"})
public class TrackingOrderController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<DisplayOrderDTO> orders = new ArrayList<>();
        orders.add(new DisplayOrderDTO(1L, "2024-11-13", 300000L, 3L, "https://via.placeholder.com/100", EnumOrderStatus.DELIVERED));
        orders.add(new DisplayOrderDTO(3L, "2024-11-13", 200000L, 2L, "https://via.placeholder.com/100", EnumOrderStatus.CANCELLED));
        orders.add(new DisplayOrderDTO(4L, "2024-11-13", 150000L, 1L, "https://via.placeholder.com/100", EnumOrderStatus.WAITING));

        // Gán danh sách orders vào request attribute
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/views/web/order/tracking-order.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
