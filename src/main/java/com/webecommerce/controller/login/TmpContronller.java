package com.webecommerce.controller.login;

import com.webecommerce.utils.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/dang-xuat")
//public class TmpContronller extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        JWTUtil.destroyToken(request, response);
//        response.sendRedirect(request.getContextPath() + "/dang-nhap");
//    }
//}