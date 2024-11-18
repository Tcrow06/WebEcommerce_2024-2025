package com.webecommerce.controller.admin.discount;

import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.service.IProductDiscountService;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/giam-gia-cho-san-pham","/chu-cua-hang/tao-giam-gia-cho-san-pham","/chu-cua-hang/chinh-sua-giam-gia-san-pham"})
public class ProductDiscountController extends HttpServlet {
    @Inject
    IProductService productService;

    @Inject
    IProductDiscountService productDiscountService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/chu-cua-hang/giam-gia-cho-san-pham")) {
            productDiscountList(request, response);
        } else if (action.equals("/chu-cua-hang/tao-giam-gia-cho-san-pham")) {
            addProductDiscount(request,response);
        } else if (action.equals("/chu-cua-hang/chinh-sua-giam-gia-san-pham")) {
            updateProductDiscount (request,response);
        }
    }

    private void addProductDiscount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDTO productDTO  = new ProductDTO();
        productDTO.setResultList(productService.getProductsFromDiscount());

        request.setAttribute(ModelConstant.MODEL, productDTO);
        request.getRequestDispatcher("/views/admin/discount/add-product-discount.jsp").forward(request, response);
    }

    private void updateProductDiscount (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String id = request.getParameter("id");

            if (id != null) {
                ProductDiscountDTO productDiscountDTO = productDiscountService.findByIdAndHaveProduct(Long.valueOf(id));
                if (productDiscountDTO != null) {
                    request.setAttribute(ModelConstant.MODEL, productDiscountDTO);
                }
            }
            request.getRequestDispatcher("/views/admin/discount/edit-product-discount.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void productDiscountList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("type");
        List<ProductDiscountDTO> productDiscountDTOS ;

        if (action == null) {
            productDiscountDTOS = productDiscountService.getProductDiscountValid();
            request.setAttribute(ModelConstant.DANG_DIEN_RA, true);
        } else {
            if (action.equals( "sap-dien-ra")) {
                productDiscountDTOS = productDiscountService.getUpcommingProductDiscount();
                request.setAttribute(ModelConstant.DANG_DIEN_RA, false);
            } else {
                productDiscountDTOS = productDiscountService.getProductDiscountValid();
                request.setAttribute(ModelConstant.DANG_DIEN_RA, true);
            }
        }

        request.setAttribute(ModelConstant.MODEL, productDiscountDTOS);
        request.getRequestDispatcher("/views/admin/discount/product-discount-list.jsp").forward(request, response);
    }
}
