package com.webecommerce.entity.order;

import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.entity.review.ProductReviewEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "orderDetail")
    private List<ProductVariantEntity> productVariants;

    @OneToOne(mappedBy = "orderDetail")
    private ReturnOrderEntity returnOrder;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @OneToMany(mappedBy = "orderDetail")
    private List<ProductReviewEntity> productReviews;

    public List<ProductVariantEntity> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariantEntity> productVariants) {
        this.productVariants = productVariants;
    }

    public ReturnOrderEntity getReturnOrder() {
        return returnOrder;
    }

    public void setReturnOrder(ReturnOrderEntity returnOrder) {
        this.returnOrder = returnOrder;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public List<ProductReviewEntity> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewEntity> productReviews) {
        this.productReviews = productReviews;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
