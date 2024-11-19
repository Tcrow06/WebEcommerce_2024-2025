package com.webecommerce.entity.review;

import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "product_review")
@Entity
public class ProductReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetailEntity orderDetail;

    @OneToOne(mappedBy = "productReview", cascade = CascadeType.ALL)
    private ReviewFeedbackEntity reviewFeedback;

    @Column(name = "number_of_stars")
    private int numberOfStars;


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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
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

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }
}
