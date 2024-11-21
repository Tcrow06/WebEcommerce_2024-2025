package com.webecommerce.dao.impl.review;


import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.review.IReviewFeedbackDAO;
import com.webecommerce.entity.review.ReviewFeedbackEntity;

public class ReviewFeedbackDAO extends AbstractDAO<ReviewFeedbackEntity> implements IReviewFeedbackDAO {
    public ReviewFeedbackDAO() {
        super(ReviewFeedbackEntity.class);
    }
}
