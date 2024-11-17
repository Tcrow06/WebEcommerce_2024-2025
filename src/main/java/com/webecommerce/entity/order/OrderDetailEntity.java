package com.webecommerce.entity.order;

import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
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

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariantEntity productVariant;


    @OneToOne(mappedBy = "orderDetail", cascade = CascadeType.ALL)
    private ReturnOrderEntity returnOrder;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_discount_id")
    private ProductDiscountEntity productDiscount;


    @OneToMany(mappedBy = "orderDetail")
    private List<ProductReviewEntity> productReviews;

    public ProductVariantEntity getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariantEntity productVariant) {
        this.productVariant = productVariant;
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

    public ProductDiscountEntity getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscountEntity productDiscount) {
        this.productDiscount = productDiscount;
    }

    public OrderDetailEntity(int quantity, ProductVariantEntity productVariant, ProductDiscountEntity productDiscount) {
        this.quantity = quantity;
        this.productVariant = productVariant;
        this.productDiscount = productDiscount;
    }
    public OrderDetailEntity(){}



}
