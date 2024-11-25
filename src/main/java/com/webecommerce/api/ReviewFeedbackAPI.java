package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.review.ProductReviewDTO;
import com.webecommerce.dto.review.ReviewFeedBackDTO;
import com.webecommerce.service.IReviewFeedBackService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-review-feedback"})
public class ReviewFeedbackAPI extends HttpServlet {

    @Inject
    private IReviewFeedBackService reviewFeedBackService;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        try {
            HttpUtils httpUtils =  HttpUtils.of(req.getReader()) ;
            ReviewFeedBackDTO reviewFeedback = httpUtils.toModel(ReviewFeedBackDTO.class);

            if (reviewFeedback != null) {
                reviewFeedback = reviewFeedBackService.save(reviewFeedback);
                if (reviewFeedback != null) {
                    mapper.writeValue(resp.getWriter(), "Thành công !");

                }
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), "Lỗi xử lý: " + e.getMessage());
        }

    }


}
