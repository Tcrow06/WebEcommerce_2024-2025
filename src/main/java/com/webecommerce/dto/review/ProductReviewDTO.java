package com.webecommerce.dto.review;

import com.webecommerce.dto.BaseDTO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class ProductReviewDTO extends BaseDTO <ProductReviewDTO> {

    Long customerId;

    String nameCustomer;

    OrderDetailDTO orderDetailDTO;

    private String content;

    private LocalDateTime date;

    private CustomerEntity customer;

    private ProductVariantEntity productVariant;

    private OrderDetailEntity orderDetail;

    private ReviewFeedbackEntity reviewFeedback;

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public OrderDetailDTO getOrderDetailDTO() {
        return orderDetailDTO;
    }

    public void setOrderDetailDTO(OrderDetailDTO orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public ProductVariantEntity getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariantEntity productVariant) {
        this.productVariant = productVariant;
    }

    public OrderDetailEntity getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailEntity orderDetail) {
        this.orderDetail = orderDetail;
    }

    public ReviewFeedbackEntity getReviewFeedback() {
        return reviewFeedback;
    }

    public void setReviewFeedback(ReviewFeedbackEntity reviewFeedback) {
        this.reviewFeedback = reviewFeedback;
    }

    private int numberOfStars;

}
