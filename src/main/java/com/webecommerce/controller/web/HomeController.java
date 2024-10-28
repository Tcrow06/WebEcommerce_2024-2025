package com.webecommerce.controller.web;

import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {

    @Inject
    private IProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // productService.getAllProducts();
        request.getRequestDispatcher("/views/web/home.jsp").forward(request,response);
    }
}
