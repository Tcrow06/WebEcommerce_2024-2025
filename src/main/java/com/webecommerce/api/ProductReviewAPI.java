package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.dto.review.ProductReviewDTO;
import com.webecommerce.service.IProductReviewService;
import com.webecommerce.utils.CustomObjectMapper;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-product-review"})
public class ProductReviewAPI extends HttpServlet {

    @Inject
    IProductReviewService productReviewService;

    CustomObjectMapper mapper = new CustomObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        try {
            String orderDetailId = req.getParameter("orderDetailId");

            if (orderDetailId != null) {
                ProductReviewDTO productReview = productReviewService.findByOrderDetailId(Long.valueOf(orderDetailId));
                if (productReview != null) {
                    mapper.writeValue(resp.getWriter(), productReview );
                }
                else mapper.writeValue(resp.getWriter(), null);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), "Lỗi xử lý: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        try {
            HttpUtils httpUtils =  HttpUtils.of(req.getReader()) ;
            ProductReviewDTO productReview = httpUtils.toModel(ProductReviewDTO.class);

            if (productReview != null) {
                productReview = productReviewService.save(productReview);
                if (productReview != null) {

                    mapper.writeValue(resp.getWriter(), "Thành công !");
                }
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), "Lỗi xử lý: " + e.getMessage());
        }
    }
}
