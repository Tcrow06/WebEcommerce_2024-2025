package com.webecommerce.controller.web;

import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.service.IOrderDetailService;
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

@WebServlet(urlPatterns = {"/danh-sach-don-hang"})
public class OrderDetailDraftController extends HttpServlet {

    @Inject
    private IOrderDetailService orderDetailService;

    @Inject
    private IOrderStatusService orderStatusService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderDetailDTO> result = orderDetailService.findAllByOrderId(2L);
        request.setAttribute("orderitemList", result);
        request.getRequestDispatcher("/views/web/order-detail-draft.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
