package com.webecommerce.dto;

import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.entity.review.ProductReviewEntity;

import javax.persistence.*;
import java.util.List;

public class OrderDetailDTO {

    private Long id;

    private int quantity;

    private ProductVariantDTO productVariant;


    private ProductDiscountDTO productDiscount;


    private List<ProductReviewDTO> productReviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductVariantDTO getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariantDTO productVariant) {
        this.productVariant = productVariant;
    }

    public ProductDiscountDTO getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscountDTO productDiscount) {
        this.productDiscount = productDiscount;
    }

    public List<ProductReviewDTO> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewDTO> productReviews) {
        this.productReviews = productReviews;
    }
}
