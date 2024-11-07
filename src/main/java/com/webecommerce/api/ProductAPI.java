package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.service.IProductService;
import com.webecommerce.service.IProductVariantService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-product"})
public class ProductAPI extends HttpServlet {
    @Inject
    IProductService productService;

    @Inject
    IProductVariantService productVariantService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json; charset=UTF-8"); // Thiết lập kiểu nội dung và mã hóa
        resp.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa UTF-8 cho phản hồi

        Map<String, Object> responseMap = new HashMap<>();
        try {
            Long idProduct = Long.valueOf(req.getParameter("id"));
            String color = req.getParameter("color");
            String size = req.getParameter("size");
            String atributenName = req.getParameter("atributeName");

            if (color != null && size != null) {
                ProductVariantDTO productVariant = productVariantService.getProductVariantByColorAndSize(idProduct, color, size);
                responseMap.put("productVariant", productVariant);
            }

            List<String> colorOrSizeAvaiable;
            if (atributenName.equals("size"))
                colorOrSizeAvaiable = productService.getListColorBySize(size, idProduct);
            else colorOrSizeAvaiable = productService.getListSizeByColor(color, idProduct);

            responseMap.put("colorOrSizeAvailable", colorOrSizeAvaiable);

            resp.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(resp.getWriter(), responseMap);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        HttpUtils httpUtils =  HttpUtils.of(req.getReader());
        ProductDTO product = httpUtils.toModel(ProductDTO.class);

        if(product != null) {
            product = productService.save(product);
            if(product != null) {
                mapper.writeValue(resp.getWriter(), product);
            } else mapper.writeValue(resp.getWriter(), "error");
        }
    }
}