package com.webecommerce.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class ReturnOrderDTO extends BaseDTO<ReturnOrderDTO> {
//    private Long id;

    private String reason;

    private LocalDate returnDate;

    private int status;
    private Long orderDetailId;
    private Long quantityReturn;

    public ReturnOrderDTO(){
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public ReturnOrderDTO(String reason, LocalDate returnDate, int status, Long quantityReturn) {
        this.reason = reason;
        this.returnDate = returnDate;
        this.status = status;
        this.quantityReturn = quantityReturn;
    }

    public Long getQuantityReturn() {
        return quantityReturn;
    }

    public void setQuantityReturn(Long quantityReturn) {
        this.quantityReturn = quantityReturn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
