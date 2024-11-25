package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.ReturnOrderDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.service.IOrderDetailService;
import com.webecommerce.service.IOrderService;
import com.webecommerce.service.IOrderStatusService;
import com.webecommerce.service.IReturnOrderService;
import com.webecommerce.utils.HttpUtils;
import com.webecommerce.utils.JWTUtil;

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
    @Inject
    private IOrderService orderService;
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
                //lay 1 order detail id
                Long orderDetailId = returnOrders.get(0).getOrderDetailId();
                if (orderDetailId != null) {
                    boolean checked = orderStatusService.changeStatus(orderDetailId, EnumOrderStatus.WAITING);
                }
                //load lai trang

//                objectMapper.writeValue(resp.getWriter(), "Return requests have been sent");

                Long customerId = JWTUtil.getIdUser(req);
                List<DisplayOrderDTO> orders = orderService.getOrderDisplay(customerId);

                req.setAttribute("orders", orders);

                req.getRequestDispatcher("/views/web/order/tracking-order.jsp").forward(req,resp);

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getWriter(), "Invalid return order data");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getWriter(), "{\"error\": \"Server error: " + e.getMessage() + "\"}");
        }


    }
}
