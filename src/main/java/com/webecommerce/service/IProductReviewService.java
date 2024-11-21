package com.webecommerce.service;

import com.webecommerce.dto.review.ProductReviewDTO;

import java.util.List;

public interface IProductReviewService {
    ProductReviewDTO save (ProductReviewDTO productReview);

    List<ProductReviewDTO> getProductReviewByProductId(Long productId);

    ProductReviewDTO findByOrderDetailId(Long OrderDetailId);
}
