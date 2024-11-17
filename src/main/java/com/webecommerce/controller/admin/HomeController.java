package com.webecommerce.controller.admin;

import com.webecommerce.constant.ModelConstant;
import com.webecommerce.service.ICategoryService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/chu-cua-hang"})
public class HomeController extends HttpServlet {

    @Inject
    ICategoryService categoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
    }
}