package com.webecommerce.controller.admin.product;

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

@WebServlet(urlPatterns = {"/aadmin/add-product-discount"})
public class ProductDiscountController extends HttpServlet {
    @Inject
    IProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDTO productDTO  = new ProductDTO();
        productDTO.setResultList(productService.findAll());

        request.setAttribute(ModelConstant.MODEL, productDTO);
        request.getRequestDispatcher("/views/admin/product/add-product-discount.jsp").forward(request, response);
    }
}
