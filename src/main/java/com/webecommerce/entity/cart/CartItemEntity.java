package com.webecommerce.entity.cart;

import com.webecommerce.entity.product.ProductVariantEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[cart_item]")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "[quantity]")
    private int quantity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", referencedColumnName = "id")
    private ProductVariantEntity productVariant;

    public ProductVariantEntity getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariantEntity productVariant) {
        this.productVariant = productVariant;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
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
}
