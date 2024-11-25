package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.review.ProductReviewDAO;
import com.webecommerce.dao.impl.review.ReviewFeedbackDAO;
import com.webecommerce.dto.review.ReviewFeedBackDTO;
import com.webecommerce.entity.review.ProductReviewEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;
import com.webecommerce.mapper.Impl.ReviewFeedbackMapper;
import com.webecommerce.service.IReviewFeedBackService;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class ReviewFeedBackService implements IReviewFeedBackService {

    @Inject
    ReviewFeedbackMapper reviewFeedbackMapper;

    @Inject
    ReviewFeedbackDAO reviewFeedbackDAO;

    @Inject
    ProductReviewDAO productReviewDAO;

    public ReviewFeedBackDTO save (ReviewFeedBackDTO reviewFeedback) {

        try {
            ReviewFeedbackEntity reviewFeedbackEntity = reviewFeedbackMapper.toEntity(reviewFeedback);

            ProductReviewEntity productReviewEntity = productReviewDAO.findById(reviewFeedback.getProductReview().getId());

            if (productReviewEntity != null && reviewFeedbackEntity != null) {
                // thiết lập liên kết
                reviewFeedbackEntity.setDate(LocalDateTime.now());
                reviewFeedbackEntity.setProductReview(productReviewEntity);
                productReviewEntity.setReviewFeedback(reviewFeedbackEntity);

                reviewFeedbackDAO.insert(reviewFeedbackEntity);
                ProductReviewEntity productReviewEntity1 = productReviewDAO.findById(reviewFeedback.getProductReview().getId());

                return reviewFeedbackMapper.toDTO(reviewFeedbackEntity);
            }
        }catch (Exception e) {
            return null;
        }

        return null;
    }

}
