package com.webecommerce.controller.admin;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.service.IOrderDetailService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/danh-sach-don-hang/chi-tiet-don-hang"})
public class OrderDetailController extends HttpServlet {

    @Inject
    private IOrderDetailService orderDetailService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("id");
        Long orderId = -1L;
        if(orderIdStr != null) {
            orderId = Long.valueOf(orderIdStr);
            EnumOrderStatus status = orderDetailService.getCurrentStatus(orderId);
        }
        List<DisplayOrderDetailDTO> result = orderDetailService.showOrderDetailAdmin(orderId);
        request.setAttribute("orderItemList", result);
        request.getRequestDispatcher("/views/admin/order-detail.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
