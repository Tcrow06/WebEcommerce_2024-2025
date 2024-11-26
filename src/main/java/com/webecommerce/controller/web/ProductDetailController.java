package com.webecommerce.controller.web;

import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.review.ProductReviewDTO;
import com.webecommerce.service.IProductReviewService;
import com.webecommerce.service.IProductService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = {"/san-pham"})
public class ProductDetailController extends HttpServlet {

    @Inject
    private IProductService productService;

    @Inject
    private IProductReviewService productReviewService;

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

                String userRole = JWTUtil.getRole(request);
                if (userRole != null) {
                    if (userRole.equals("OWNER"))
                        request.setAttribute(ModelConstant.ROLE, userRole);
                }

                List<ProductReviewDTO> productReviewDTOList = productReviewService.getProductReviewByProductId(product.getId());

                request.setAttribute(ModelConstant.REVIEW, productReviewDTOList);
                request.setAttribute(ModelConstant.MODEL, product);

                request.setAttribute(ModelConstant.SUGGEST, productService.findProductSuggestion(product.getCategory().getId(),4,product.getId()));
                request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);
                return;
            }
        }
        request.getRequestDispatcher("/views/web/product-not-found.jsp").forward(request, response);
    }
}
