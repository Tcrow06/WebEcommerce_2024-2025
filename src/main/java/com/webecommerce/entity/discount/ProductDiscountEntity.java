package com.webecommerce.entity.discount;

import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.product.ProductEntity;
import org.hibernate.engine.internal.JoinHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_discount")
public class ProductDiscountEntity extends DiscountEntity {

    @OneToMany(mappedBy = "productDiscount")
    private List<OrderDetailEntity> orderDetails;


    @OneToOne(mappedBy = "productDiscount", cascade = CascadeType.ALL)
    private ProductEntity product;

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}