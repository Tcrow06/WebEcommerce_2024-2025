package com.webecommerce.controller.web;

import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {

    @Inject
    private IProductService productService;

    @Inject
    ICustomerDAO customerDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/web/home.jsp").forward(request,response);
    }
}
