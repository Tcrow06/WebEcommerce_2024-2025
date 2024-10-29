package com.webecommerce.entity.discount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bill_discount")
public class BillDiscountEntity extends DiscountEntity {

    @Column(name = "minimum_invoice_amount")
    private double minimumInvoiceAmount;

    @Column(name = "invoice_type")
    private String invoiceType;

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
