package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.service.ICategoryService;
import com.webecommerce.service.IProductService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-product"})
public class ProductAPI extends HttpServlet {
    @Inject
    IProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        HttpUtils httpUtils =  HttpUtils.of(req.getReader()) ;
        ProductDTO product = httpUtils.toModel(ProductDTO.class);

        if (product != null) {
            product = productService.save(product) ;
            if (product != null) {
                mapper.writeValue(resp.getWriter(), product);
            } else mapper.writeValue(resp.getWriter(), "error");
        }
    }
}
