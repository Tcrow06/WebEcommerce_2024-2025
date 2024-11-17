package com.webecommerce.controller.web;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.service.IOrderService;
import com.webecommerce.service.IOrderStatusService;

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
    @Inject
    private IOrderStatusService orderStatusService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Long customerId = JWTUtil.getIdUser(request);
        Long customerId = 1L;
        List<DisplayOrderDTO> orders = orderService.getOrderDisplay(customerId);

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/views/web/order/tracking-order.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderDetailIdStr = request.getParameter("firstIdOrderDetail");

        if (orderDetailIdStr != null) {
            Long orderDetailId = Long.parseLong(orderDetailIdStr);
            boolean updateStatusReceive = orderStatusService.changeStatus(orderDetailId, EnumOrderStatus.RECEIVED);
        }
        Long customerId = 1L;
        List<DisplayOrderDTO> orders = orderService.getOrderDisplay(customerId);

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/web/order/tracking-order.jsp").forward(request,response);
    }
}
