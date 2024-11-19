package com.webecommerce.dto.review;

import com.webecommerce.dto.BaseDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReviewFeedBackDTO extends BaseDTO <ReviewFeedBackDTO> {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    private LocalDateTime createDate;

    private ProductReviewDTO productReview;

    public ProductReviewDTO getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReviewDTO productReview) {
        this.productReview = productReview;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'tháng' M 'năm' yyyy, HH:mm", new Locale("vi", "VN"));
        return this.createDate.format(formatter);
    }
}
