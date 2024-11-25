package com.webecommerce.controller.web;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;
import com.webecommerce.service.IOrderStatusService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/thong-bao"})
public class NotificationController extends HttpServlet {
    @Inject
    private IOrderStatusService orderStatusService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = JWTUtil.getIdUser(req);

        List<TransferListOderStatusDTO> result = orderStatusService.getStatusOrders(id, EnumOrderStatus.RECEIVED);

        if (result != null) {
            req.setAttribute("orders", result);
        }
        req.getRequestDispatcher("/views/web/notification.jsp").forward(req, resp);
    }
}
