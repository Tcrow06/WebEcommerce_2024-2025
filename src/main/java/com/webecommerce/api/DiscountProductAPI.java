package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.service.IProductDiscountService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ({"/api-product-discount"})
public class DiscountProductAPI extends HttpServlet {
    @Inject
    IProductDiscountService productDiscountService;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpUtils httpUtils =  HttpUtils.of(request.getReader());
        ProductDiscountDTO productDiscount = httpUtils.toModel(ProductDiscountDTO.class);

        if(productDiscount != null) {
            if (productDiscount.getId() == null)
                addProductDiscount(request,response,productDiscount);
            else updateProductDiscount(request,response,productDiscount);
        }
        else response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpUtils httpUtils =  HttpUtils.of(request.getReader());
        ProductDiscountDTO productDiscount = httpUtils.toModel(ProductDiscountDTO.class);

        if(productDiscount != null) {
            cancelProductDiscount(request, response, productDiscount);
        }
        else response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void addProductDiscount (HttpServletRequest req, HttpServletResponse resp, ProductDiscountDTO productDiscount) throws IOException {
        productDiscount = productDiscountService.save(productDiscount);
        if(productDiscount != null) {
            mapper.writeValue(resp.getWriter(), "Thêm giảm giá thành công !");
        } else mapper.writeValue(resp.getWriter(), "error");
    }

    private void updateProductDiscount (HttpServletRequest req, HttpServletResponse resp, ProductDiscountDTO productDiscount) throws IOException {
        productDiscount = productDiscountService.update(productDiscount);
        if(productDiscount != null) {
            mapper.writeValue(resp.getWriter(), "Cập nhật giảm giá thành công !");
        } else mapper.writeValue(resp.getWriter(), "error");
    }

    private void cancelProductDiscount(HttpServletRequest req, HttpServletResponse resp, ProductDiscountDTO productDiscount) throws IOException {
        productDiscount = productDiscountService.cancelProductDiscount(productDiscount.getId());
        if(productDiscount != null) {
            mapper.writeValue(resp.getWriter(), "Hủy giảm giá thành công !");
        } else mapper.writeValue(resp.getWriter(), "error");
    }
}
