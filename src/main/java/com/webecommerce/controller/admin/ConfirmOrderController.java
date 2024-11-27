package com.webecommerce.controller.admin;


import com.webecommerce.dao.impl.order.OrderDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.service.IOrderService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/xac-nhan-don-hang"})
public class ConfirmOrderController extends HttpServlet {
    @Inject
    private IOrderService orderService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<DisplayOrderDTO> orders = orderService.getOrderDisplay();

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/views/admin/confirm-order.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr != null) {
            Long orderId = Long.parseLong(orderIdStr);
            boolean result = orderService.changeConfirmStatus(orderId);
            if (result) {
                response.sendRedirect("/chu-cua-hang/xac-nhan-don-hang?success=true");
            } else {
                response.sendRedirect("/chu-cua-hang/xac-nhan-don-hang?error=true");
            }
        } else {
            response.sendRedirect("/chu-cua-hang/xac-nhan-don-hang?error=true");
        }
    }
}
