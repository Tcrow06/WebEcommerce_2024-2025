package com.webecommerce.dao.review;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.review.ProductReviewEntity;

import java.util.List;

public interface IProductReviewDAO extends GenericDAO<ProductReviewEntity> {

    List<ProductReviewEntity> getProductReviewByProduct (Long productId);
}
