package com.webecommerce.controller.web;

import com.webecommerce.dao.impl.ProductDAO;
import com.webecommerce.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Test kết nối với db
        Product product = new Product("quan jean", "rach");
        productDAO.insert(product);
        request.getRequestDispatcher("/views/web/home.jsp").forward(request,response);
    }
}
