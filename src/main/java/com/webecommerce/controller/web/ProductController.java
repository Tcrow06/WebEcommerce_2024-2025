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
import java.util.List;

@WebServlet(urlPatterns = {"/san-pham"})
public class ProductController extends HttpServlet {

    @Inject
    private IProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("ten-san-pham-o-day".equals(action)) {
            request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);
        } else {
            ProductDTO product = new ProductDTO();
            product.setResultList(
                    productService.findAll()
            );

            request.setAttribute(ModelConstant.MODEL,product);
            request.getRequestDispatcher("/views/web/product-list.jsp").forward(request, response);
        }
    }
}
