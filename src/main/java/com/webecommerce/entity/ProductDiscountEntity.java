package com.webecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_discount")
public class ProductDiscountEntity extends DiscountEntity {

    @ManyToOne
    private ProductEntity product;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}