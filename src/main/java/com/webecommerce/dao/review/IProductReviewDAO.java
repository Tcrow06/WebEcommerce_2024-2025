package com.webecommerce.dao.review;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.review.ProductReviewEntity;

import java.util.List;

public interface IProductReviewDAO extends GenericDAO<ProductReviewEntity> {

    List<ProductReviewEntity> getProductReviewByProduct (Long productId);

    ProductReviewEntity getProductReviewByOrderDetailId (Long OrderDetailId);

    int calculateStarByProduct(Long productId);

    int countProductReviewByProduct(Long productId);
}
