package com.webecommerce.dto.discount;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDiscountDTO extends DiscountDTO {
    private double minimumInvoiceAmount;

    private String invoiceType;
    private String code;

    public double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
    private double maximumAmount;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
