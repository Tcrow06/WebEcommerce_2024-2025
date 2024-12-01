package com.webecommerce.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.PlacedOrder.ProductOrderDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.webecommerce.service.IOrderService;
import com.webecommerce.utils.JWTUtil;
import org.hibernate.criterion.Order;

@WebServlet(urlPatterns = {"/kiem-tra-san-pham"})
public class PlacedOrderController extends HttpServlet {


    @Inject
    private IOrderService orderService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session=request.getSession();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> jsonResponse = new HashMap<>();

            // Parse JSON thành danh sách sản phẩm
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            CheckOutRequestDTO checkOutRequestDTO = objectMapper.readValue(request.getReader(), CheckOutRequestDTO.class);
            if(JWTUtil.verifyToken(JWTUtil.getToken(request))==null){
                session.setAttribute("orderNotHandler",checkOutRequestDTO);
                jsonResponse.put("status","warning");
                jsonResponse.put("redirectUrl", "/dang-nhap?action=login&message=please-login-before-check-out&alert=danger&send-direction=gio-hang");
                objectMapper.writeValue(response.getWriter(), jsonResponse);
                return;
            }

            checkOutRequestDTO.setIdUser(JWTUtil.getIdUser(request));
            session.setAttribute("orderHandler",checkOutRequestDTO);
            OrderDTO orderDTO = orderService.findInfoCheckOut(checkOutRequestDTO);
            if(orderDTO.getStatus().equals("error")){
                jsonResponse.put("message",orderDTO.getMessage());
                jsonResponse.put("status","error");
            }else{
                jsonResponse.put("redirectUrl", "/thanh-toan");
                jsonResponse.put("order",orderDTO);
                jsonResponse.put("status","success");
            }

            objectMapper.writeValue(response.getWriter(), jsonResponse);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
