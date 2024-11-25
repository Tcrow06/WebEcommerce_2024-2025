package com.webecommerce.controller.admin;

import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.service.impl.OrderService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/danh-sach-don-hang"})
public class ListOrderController extends HttpServlet {
    @Inject
    private OrderService orderService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DisplayOrderDTO> orders = orderService.getListOrder();

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/admin/list-order.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
