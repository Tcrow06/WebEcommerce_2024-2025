package com.webecommerce.controller.web;

import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = {"/san-pham"})
public class ProductDetailController extends HttpServlet {

    @Inject
    private IProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = null ;
        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id != null) {
            ProductDTO product = productService.getProductById(id);
            if (product != null) {
                request.setAttribute(ModelConstant.MODEL, product);
                request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect("/danh-sach-san-pham");
    }
}
