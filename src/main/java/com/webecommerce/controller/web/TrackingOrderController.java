package com.webecommerce.controller.web;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.service.IOrderService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
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
    @Inject
    private IOrderService orderService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Long customerId = JWTUtil.getIdUser(request);
        Long customerId = 1L;
        List<DisplayOrderDTO> orders = orderService.getOrderDisplay(customerId);

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/views/web/order/tracking-order.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
