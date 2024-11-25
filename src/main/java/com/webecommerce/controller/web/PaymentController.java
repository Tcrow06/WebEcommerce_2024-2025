package com.webecommerce.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.PlacedOrder.ProductOrderDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.webecommerce.service.IOrderInfoService;
import com.webecommerce.service.IOrderService;
import com.webecommerce.utils.HttpUtils;
import com.webecommerce.utils.JWTUtil;
import org.hibernate.criterion.Order;


@WebServlet(urlPatterns = {"/thanh-toan"})
public class PaymentController extends HttpServlet {

    @Inject
    private IOrderInfoService orderInfoService;

    @Inject
    private IOrderService orderService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Long idUser  = JWTUtil.getIdUser(request);
            List<OrderInfoDTO> orderInfos = orderInfoService.getOrderInfoByCustomerId(idUser);

            String state = request.getParameter("order");
            HttpSession session = request.getSession();
            if(state==null) {
                state = session.getAttribute("order").toString();
            } else {
                session.removeAttribute("order");
                session.setAttribute("order",state);
            }

            // Giải mã dữ liệu
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            if (state != null && !state.isEmpty()) {
                String decodedState = URLDecoder.decode(state, StandardCharsets.UTF_8);
                OrderDTO orderDTO = objectMapper.readValue(decodedState, OrderDTO.class);
                String orderDTOJson = objectMapper.writeValueAsString(orderDTO);
                request.setAttribute("orderDTO",orderDTO);
                request.setAttribute("orderDTOJson",orderDTOJson);

                request.setAttribute("orderInfos", orderInfos);
                request.getRequestDispatcher("/views/web/payment.jsp").forward(request, response);
            } else {
                response.sendRedirect("/error");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        OrderDTO orderDTO =  HttpUtils.of(request.getReader()).toModel(OrderDTO.class);
        Map<String, Object> jsonResponse = new HashMap<>();
        orderDTO = orderService.findInfoPayment(orderDTO, JWTUtil.getIdUser(request));

        if (orderDTO.getStatus().contains("error")) {
            jsonResponse.put("redirectUrl", "/thanh-toan");
            jsonResponse.put("message",orderDTO.getMessage().toString());
            jsonResponse.put("status","error");
        } else if (orderDTO.getStatus().contains("warning")) {
            jsonResponse.put("message",orderDTO.getMessage().toString());
            jsonResponse.put("status","warning");
        } else {
            jsonResponse.put("redirectUrl", "/thanh-toan");
            jsonResponse.put("message",orderDTO.getMessage().toString());
            jsonResponse.put("status","success");
        }
        objectMapper.writeValue(response.getWriter(), jsonResponse);
    }
}
