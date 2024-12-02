package com.webecommerce.controller.web;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.service.ICustomerService;
import com.webecommerce.service.IOrderService;
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

@WebServlet(urlPatterns = {"/trang-chu/don-hang"})
public class TrackingOrderController extends HttpServlet {
    @Inject
    private IOrderService orderService;
    @Inject
    private IOrderStatusService orderStatusService;
    @Inject
    private ICustomerService customerService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Long customerId = JWTUtil.getIdUser(request);
            List<DisplayOrderDTO> orders = orderService.getOrderDisplay(customerId);
            request.setAttribute("orders", orders);

            request.getRequestDispatcher("/views/web/order/tracking-order.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long customerId = JWTUtil.getIdUser(request);

        String orderDetailIdStr = request.getParameter("firstIdOrderDetail");
        String actionType = request.getParameter("actionType");
        String totalAmount = request.getParameter("totalAmount");
        double total = -1D;
        if(totalAmount != null) {
            total = Double.parseDouble(totalAmount);
        }

        if (orderDetailIdStr != null) {
            Long orderDetailId = Long.parseLong(orderDetailIdStr);
            if ("CONFIRM".equals(actionType)) {
                boolean updateStatusReceive = orderStatusService.changeStatus(orderDetailId, EnumOrderStatus.RECEIVED);
                boolean updateLoyalPoint = customerService.updateLoyalPoint(total, customerId);
            }
            else if("CANCEL".equals(actionType)) {
                boolean updateStatusCancle = orderStatusService.changeStatus(orderDetailId, EnumOrderStatus.CANCELLED);
            }
        }
        List<DisplayOrderDTO> orders = orderService.getOrderDisplay(customerId);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/web/order/tracking-order.jsp").forward(request,response);
    }
}
