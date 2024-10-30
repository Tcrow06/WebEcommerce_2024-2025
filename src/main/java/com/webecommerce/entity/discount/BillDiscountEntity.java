package com.webecommerce.entity.discount;

import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import org.hibernate.criterion.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill_discount")
public class BillDiscountEntity extends DiscountEntity {

    @Column(name = "minimum_invoice_amount")
    private double minimumInvoiceAmount;

    @Column(name = "invoice_type")
    private String invoiceType;

    // Mỗi bill discount đáp ứng cho 1 order
    @OneToOne(mappedBy = "billDiscount")
    private OrderEntity order;

    @Override
    public OrderEntity getOrder() {
        return order;
    }

    @Override
    public void setOrder(OrderEntity order) {
        this.order = order;
    }

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
