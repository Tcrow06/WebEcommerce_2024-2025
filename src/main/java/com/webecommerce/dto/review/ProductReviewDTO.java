package com.webecommerce.dto.review;

import com.webecommerce.dto.BaseDTO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ProductReviewDTO extends BaseDTO <ProductReviewDTO> {

    Long customerId;

    String nameCustomer;

    private String content;

    private LocalDateTime date;

    private OrderDetailEntity orderDetail;

    private ReviewFeedBackDTO reviewFeedback;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'tháng' M 'năm' yyyy, HH:mm", new Locale("vi", "VN"));
        return this.date.format(formatter);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ReviewFeedBackDTO getReviewFeedback() {
        return reviewFeedback;
    }

    public void setReviewFeedback(ReviewFeedBackDTO reviewFeedback) {
        this.reviewFeedback = reviewFeedback;
    }

    private int numberOfStars;

    public OrderDetailEntity getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailEntity orderDetail) {
        this.orderDetail = orderDetail;
    }
}
