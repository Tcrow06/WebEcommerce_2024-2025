package com.webecommerce.controller.web;

import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.service.ICategoryService;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/danh-sach-san-pham"})
public class ProductController extends HttpServlet {

    @Inject
    private IProductService productService;

    @Inject
    private ICategoryService categoryService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDTO product ;

        String category = request.getParameter("category");
        String brand = request.getParameter("brand");

        product = new ProductDTO();
        if (category != null) {
            product.setResultList(productService.findProductsByCategoryCode(category));
        } else if (brand != null) {
            product.setResultList(productService.findProductsByBrand(brand));
        } else product.setResultList(productService.findAll());

        request.setAttribute(ModelConstant.MODEL2, productService.getBrands());
        request.setAttribute(ModelConstant.MODEL1, categoryService.findAll());
        request.setAttribute(ModelConstant.MODEL,product);
        request.getRequestDispatcher("/views/web/product-list.jsp").forward(request, response);
    }
}
