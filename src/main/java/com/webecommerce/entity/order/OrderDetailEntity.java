package com.webecommerce.entity.order;

import com.webecommerce.entity.product.ProductVariantEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "end_price")
    private double endPrice;

    @Column(name = "quantity")
    private int quantity;

    // Danh sách các product variant của mỗi order detail
    @OneToMany(mappedBy = "orderDetail", cascade = CascadeType.ALL)
    private List<ProductVariantEntity> productVariants;

    // Mỗi order detail thuộc về một order duy nhất
    @ManyToOne
    private OrderEntity order;

    // Mỗi order detail trả được 1 lần
    @OneToOne(mappedBy = "orderDetail")
    private ReturnOrderEntity returnOrder;

    public List<ProductVariantEntity> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariantEntity> productVariants) {
        this.productVariants = productVariants;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ReturnOrderEntity getReturnOrder() {
        return returnOrder;
    }

    public void setReturnOrder(ReturnOrderEntity returnOrder) {
        this.returnOrder = returnOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
