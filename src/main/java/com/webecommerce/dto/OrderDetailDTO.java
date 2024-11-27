package com.webecommerce.dto;

import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.entity.review.ProductReviewEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailDTO {

    private Long id;

    private int quantity;

    private ProductVariantDTO productVariant;

    private ProductDiscountDTO productDiscount;

    private List<ProductReviewDTO> productReviews;

    private double total;

    public double getTotal() {
        return total;
    }

    private void calculateTotal() {
        double sum= productVariant.getPrice()*quantity;
        if(productDiscount!=null &&
            (productDiscount.getEndDate().isAfter(LocalDateTime.now()) || productDiscount.getEndDate().equals(LocalDateTime.now()))
            && (productDiscount.getStartDate().isBefore(LocalDateTime.now())||productDiscount.getStartDate().equals(LocalDateTime.now()))
        ){
            sum *= (double) (100 - productDiscount.getDiscountPercentage()) /100;
        }
        this.total= sum;

    }

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


    public OrderDetailDTO(int quantity, ProductVariantDTO productVariant, ProductDiscountDTO productDiscount) {
        this.quantity = quantity;
        this.productVariant = productVariant;
        this.productDiscount = productDiscount;
        calculateTotal();
    }

    public OrderDetailDTO() {
    }
}
