package com.webecommerce.controller.admin.product;
import com.webecommerce.constant.ModelConstant;
import com.webecommerce.service.ICategoryService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/aadmin/them-san-pham", "/aadmin/danh-sach-san-pham"})
public class ProductController extends HttpServlet {
    @Inject
    ICategoryService categoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/aadmin/them-san-pham")) {
            addProduct(request, response);
        } else if (action.equals("/aadmin/danh-sach-san-pham")) {
            productList(request, response);
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(ModelConstant.MODEL, categoryService.findAll());
        request.getRequestDispatcher("/views/admin/product/add-product.jsp").forward(request, response);
    }

    private void productList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(ModelConstant.MODEL, categoryService.findAll());
        request.getRequestDispatcher("/views/admin/product/product-list.jsp").forward(request, response);
    }
}
