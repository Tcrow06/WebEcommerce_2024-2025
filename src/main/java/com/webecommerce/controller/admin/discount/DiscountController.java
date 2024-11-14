package com.webecommerce.controller.admin.discount;

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

@WebServlet(urlPatterns = {"/chu-cua-hang/giam-gia-cho-san-pham","/chu-cua-hang/giam-gia-cho-don-hang","/chu-cua-hang/tao-giam-gia-cho-san-pham","/chu-cua-hang/tao-giam-gia-cho-don-hang"})
public class DiscountController extends HttpServlet {
    @Inject
    IProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/chu-cua-hang/giam-gia-cho-san-pham")) {
            productDiscountList(request,response);
        } else if (action.equals("/chu-cua-hang/giam-gia-cho-don-hang")) {
           billDiscountList(request,response);
        } else if (action.equals("/chu-cua-hang/tao-giam-gia-cho-san-pham")) {
            productDiscount(request,response);
        } else if (action.equals("/chu-cua-hang/tao-giam-gia-cho-don-hang")) {
            billDiscount(request,response);
        }
    }

    private void productDiscount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDTO productDTO  = new ProductDTO();
        productDTO.setResultList(productService.findAll());

        request.setAttribute(ModelConstant.MODEL, productDTO);
        request.getRequestDispatcher("/views/admin/discount/add-product-discount.jsp").forward(request, response);
    }

    private void billDiscount (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/discount/add-bill-discount.jsp").forward(request, response);
    }

    private void billDiscountList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/discount/bill-discount-list.jsp").forward(request, response);
    }

    private void productDiscountList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/discount/product-discount-list.jsp").forward(request, response);
    }
}
