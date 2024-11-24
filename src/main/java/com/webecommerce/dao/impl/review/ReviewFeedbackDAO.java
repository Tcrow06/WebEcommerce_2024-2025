package com.webecommerce.dao.impl.review;


import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.review.IReviewFeedbackDAO;
import com.webecommerce.entity.review.ProductReviewEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;

import javax.persistence.NoResultException;
import java.util.logging.Level;

public class ReviewFeedbackDAO extends AbstractDAO<ReviewFeedbackEntity> implements IReviewFeedbackDAO {
    public ReviewFeedbackDAO() {
        super(ReviewFeedbackEntity.class);
    }

    @Override
    public ReviewFeedbackEntity findReviewFeedbackByProductReviewId(Long productReviewId) {
        String query = "SELECT d FROM ReviewFeedbackEntity d " +
                "WHERE d.productReview.id = :productReviewId";
        try {
            return entityManager.createQuery(query, ReviewFeedbackEntity.class)
                    .setParameter("productReviewId", productReviewId)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy review nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy review", e);
            return null;
        }
    }
}
