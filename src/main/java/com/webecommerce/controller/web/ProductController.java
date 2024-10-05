package com.webecommerce.controller.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/san-pham"})
public class ProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("chi-tiet-san-pham")) {
            request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("/views/web/product-list.jsp").forward(request,response);
        }
    }
}
