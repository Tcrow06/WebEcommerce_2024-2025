package com.webecommerce.entity.review;

import com.webecommerce.entity.people.OwnerEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review_feedback")
public class ReviewFeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne(mappedBy = "reviewFeedback")
    private OwnerEntity owner;

    @OneToOne(mappedBy = "reviewFeedback")
    private ProductReviewEntity productReview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public ProductReviewEntity getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReviewEntity productReview) {
        this.productReview = productReview;
    }
}
