package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.ReturnOrderDTO;
import com.webecommerce.service.IOrderDetailService;
import com.webecommerce.service.IOrderStatusService;
import com.webecommerce.service.IReturnOrderService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api-return-order"})
public class ReturnOrderAPI extends HttpServlet {
    @Inject
    private IReturnOrderService returnOrderService;
    @Inject
    private IOrderStatusService orderStatusService;
    @Inject
    private IOrderDetailService orderDetailService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HttpUtils httpUtils = HttpUtils.of(req.getReader());
            ReturnOrderDTO returnOrderList = httpUtils.toModel(ReturnOrderDTO.class);

            if (returnOrderList != null) {
                List<ReturnOrderDTO> returnOrders = returnOrderList.getResultList();
                for (ReturnOrderDTO returnOrder : returnOrders) {
                    returnOrderService.save(returnOrder);
                }
                objectMapper.writeValue(resp.getWriter(), "Return requests have been sent");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getWriter(), "Invalid return order data");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getWriter(), "{\"error\": \"Server error: " + e.getMessage() + "\"}");
        }

        // cap nhat trang thai va load lai trang
        String[] selectedItems = req.getParameterValues("productList");
        String orderIdStr = selectedItems[0];
        if (orderIdStr != null) {
            Long orderId = Long.valueOf(orderIdStr);
            boolean checked = orderStatusService.changeStatus(orderId);
            if(checked) {
                System.out.println("Thanh cong");
            }
        }

        List<OrderDetailDTO> result = orderDetailService.findAllByOrderId(2L);
        req.setAttribute("orderitemList", result);
        req.getRequestDispatcher("/views/web/order-detail-draft.jsp").forward(req,resp);
    }
}
