package com.webecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.entity.people.CustomerEntity;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {

    private Long id;

    private double shippingFee;

    private BillDiscountDTO billDiscount;

    private List<OrderStatusDTO> orderStatuses;

    private List<OrderDetailDTO> orderDetails;

    //Thông tin chio tiết đơn hàng
    private OrderInfoDTO orderInfoDTO;

    private double total;
    private double maximumDiscount=0;

    private String status;
    private StringBuilder message = new StringBuilder();

    private String paymentMethod;

    public double getMaximumDiscount() {
        return maximumDiscount;
    }


    public double getTotal() {
        return total;
    }

    public boolean calculateTotal(){
        double sum= 0;
        for (OrderDetailDTO o: orderDetails) {
            sum +=o.getTotal();
        }
        this.total = sum;
        if(billDiscount!=null){
            if(billDiscount.getMinimumInvoiceAmount() > total){
                return false;
            }
            if(billDiscount.getMaximumAmount()>(total*billDiscount.getDiscountPercentage()/100)){
                this.maximumDiscount = total*billDiscount.getDiscountPercentage()/10;
            }else {
                this.maximumDiscount = billDiscount.getMaximumAmount();
            }
        }else{
            this.maximumDiscount=0;
        }
        return true;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public void setMessage(StringBuilder message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BillDiscountDTO getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(BillDiscountDTO billDiscount) {
        this.billDiscount = billDiscount;
    }

    public List<OrderStatusDTO> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatusDTO> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderInfoDTO getOrderInfoDTO() {
        return orderInfoDTO;
    }

    public void setOrderInfoDTO(OrderInfoDTO orderInfoDTO) {
        this.orderInfoDTO = orderInfoDTO;
    }

    public OrderDTO() {
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
