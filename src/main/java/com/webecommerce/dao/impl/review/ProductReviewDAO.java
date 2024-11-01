package com.webecommerce.dao.impl.review;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.review.IProductReviewDAO;
import com.webecommerce.entity.review.ProductReviewEntity;

public class ProductReviewDAO extends AbstractDAO<ProductReviewEntity> implements IProductReviewDAO {
    public ProductReviewDAO() {
        super(ProductReviewEntity.class);
    }
}
