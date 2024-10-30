package com.webecommerce.entity.discount;

import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.product.ProductEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_discount")
public class ProductDiscountEntity extends DiscountEntity {

    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    private OrderEntity order;

    @Override
    public OrderEntity getOrder() {
        return order;
    }

    @Override
    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}