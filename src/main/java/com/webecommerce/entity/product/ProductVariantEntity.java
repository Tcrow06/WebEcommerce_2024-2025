package com.webecommerce.entity.product;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.review.ProductReviewEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_variant")
public class ProductVariantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumProductStatus status;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    private OrderDetailEntity orderDetail;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private CartItemEntity cartItem;

    @OneToMany(mappedBy = "productVariant")
    private List<ProductReviewEntity> productReviews = new ArrayList<>();

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public OrderDetailEntity getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailEntity orderDetail) {
        this.orderDetail = orderDetail;
    }

    public CartItemEntity getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItemEntity cartItem) {
        this.cartItem = cartItem;
    }

    public List<ProductReviewEntity> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewEntity> productReviews) {
        this.productReviews = productReviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EnumProductStatus getStatus() {
        return status;
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
