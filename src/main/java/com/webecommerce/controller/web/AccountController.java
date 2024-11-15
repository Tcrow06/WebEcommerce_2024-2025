package com.webecommerce.controller.web;

import com.auth0.jwt.JWT;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.entity.other.AddressEntity;
import com.webecommerce.service.IOrderInfoService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/thong-tin-ca-nhan"})
public class AccountController extends HttpServlet {

    @Inject
    private IOrderInfoService orderInfoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long customerId = JWTUtil.getIdUser(request);
        List<OrderInfoDTO> orderInfos = orderInfoService.getOrderInfoByCustomerId(customerId);


        request.setAttribute("id", customerId);
        request.setAttribute("orderInfos", orderInfos);

        request.getRequestDispatcher("/views/web/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


    }
}
