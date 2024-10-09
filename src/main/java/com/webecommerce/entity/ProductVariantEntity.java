package com.webecommerce.entity;

import com.webecommerce.constant.EnumProductStatus;

import javax.persistence.*;

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

    // Mỗi một biến thể chỉ thuộc về một sản phẩm
    @ManyToOne
    private ProductEntity product;

    public ProductEntity getProductEntity() {
        return product;
    }

    public void setProductEntity(ProductEntity product) {
        this.product = product;
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
