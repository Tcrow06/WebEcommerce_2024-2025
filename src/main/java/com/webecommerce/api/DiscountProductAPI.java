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

@WebServlet ({"/api-product-discount","/api-huy-giam-gia"})
public class DiscountProductAPI extends HttpServlet {
    @Inject
    IProductDiscountService productDiscountService;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        HttpUtils httpUtils =  HttpUtils.of(request.getReader());
        ProductDiscountDTO productDiscount = httpUtils.toModel(ProductDiscountDTO.class);

        if(productDiscount != null) {
            String action = request.getServletPath();
            if (action.equals("/api-product-discount")) {
                updateProductDiscount(request, response,productDiscount);
            } else if (action.equals("/api-huy-giam-gia")) {
                cancelProductDiscount(request, response, productDiscount);
            }
        }
        else response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
    }

    private void updateProductDiscount(HttpServletRequest req, HttpServletResponse resp, ProductDiscountDTO productDiscount) throws IOException {
        if (productDiscount.getId() == null) {
            productDiscount = productDiscountService.save(productDiscount);
            if(productDiscount != null) {
                mapper.writeValue(resp.getWriter(), "Thêm giảm giá thành công !");
            } else mapper.writeValue(resp.getWriter(), "error");
        }
        else {
            productDiscount = productDiscountService.update(productDiscount);
            if(productDiscount != null) {
                mapper.writeValue(resp.getWriter(), "Cập nhật giảm giá thành công !");
            } else mapper.writeValue(resp.getWriter(), "error");
        }
    }

    private void cancelProductDiscount(HttpServletRequest req, HttpServletResponse resp, ProductDiscountDTO productDiscount) throws IOException {
        productDiscount = productDiscountService.cancelProductDiscount(productDiscount.getId());
        if(productDiscount != null) {
            mapper.writeValue(resp.getWriter(), "Hủy giảm giá thành công !");
        } else mapper.writeValue(resp.getWriter(), "error");
    }
}
