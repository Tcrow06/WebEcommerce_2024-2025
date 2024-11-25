package com.webecommerce.dao.review;


import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.review.ReviewFeedbackEntity;

public interface IReviewFeedbackDAO extends GenericDAO<ReviewFeedbackEntity> {
    ReviewFeedbackEntity findReviewFeedbackByProductReviewId(Long productReviewId);
}
