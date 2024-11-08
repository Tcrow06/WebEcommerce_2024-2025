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

@WebServlet ("/api-product-discount")
public class DiscountProductAPI extends HttpServlet {
    @Inject
    IProductDiscountService productDiscountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        HttpUtils httpUtils =  HttpUtils.of(req.getReader());
        ProductDiscountDTO productDiscount = httpUtils.toModel(ProductDiscountDTO.class);

        if(productDiscount != null) {
            productDiscount = productDiscountService.save(productDiscount);
            if(productDiscount != null) {
                mapper.writeValue(resp.getWriter(), productDiscount);
            } else mapper.writeValue(resp.getWriter(), "error");
        }
    }
}
