package com.webecommerce.controller.web;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.service.IOrderDetailService;
import com.webecommerce.service.IOrderStatusService;
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

@WebServlet(urlPatterns = {"/trang-chu/don-hang/danh-sach-don-hang"})
public class OrderDetailDraftController extends HttpServlet {
    @Inject
    private IOrderDetailService orderDetailService;

    @Inject
    private IOrderStatusService orderStatusService;

    @Inject
    private IOrderDetailDAO orderDetailDAO;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("id");
        if(orderIdStr != null) {
            Long orderId = Long.valueOf(orderIdStr);
            EnumOrderStatus status = orderDetailService.getCurrentStatus(orderId);
            List<DisplayOrderDetailDTO> result = orderDetailService.showOrderDetailReview(orderId, status, EnumProductStatus.SELLING);
            request.setAttribute("customerId",JWTUtil.getIdUser(request));
            request.setAttribute("orderItemList", result);
            request.setAttribute("status", status);
        }
        request.getRequestDispatcher("/views/web/order-detail.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
