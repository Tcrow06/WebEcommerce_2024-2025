package com.webecommerce.entity.discount;

import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bill_discount")
public class BillDiscountEntity extends DiscountEntity {

    @Column(name = "minimum_invoice_amount")
    private double minimumInvoiceAmount;

    @Column(name = "invoice_type")
    private String invoiceType;

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "billDiscount")
    private List<OrderEntity> orders;


    public double getMinimumInvoiceAmount() {
        return minimumInvoiceAmount;
    }

    public void setMinimumInvoiceAmount(double minimumInvoiceAmount) {
        this.minimumInvoiceAmount = minimumInvoiceAmount;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
}
