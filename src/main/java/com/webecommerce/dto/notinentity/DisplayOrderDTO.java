package com.webecommerce.dto.notinentity;

import com.webecommerce.constant.EnumOrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DisplayOrderDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Double totalOrder;
    private Long allQuantity;
    private String imgUrl;
    private EnumOrderStatus status;

    public DisplayOrderDTO(Long id, LocalDateTime dateTime, Double totalOrder, Long allQuantity, String imgUrl, EnumOrderStatus status) {
        this.id = id;
        this.status = status;
        this.dateTime = dateTime;
        this.allQuantity = allQuantity;
        this.totalOrder = totalOrder;
        this.imgUrl = imgUrl;
    }

    public DisplayOrderDTO(Long id, LocalDateTime dateTime, Double totalOrder, Long allQuantity, String imgUrl) {
        this.id = id;
        this.dateTime = dateTime;
        this.allQuantity = allQuantity;
        this.totalOrder = totalOrder;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Long getAllQuantity() {
        return allQuantity;
    }

    public void setAllQuantity(Long allQuantity) {
        this.allQuantity = allQuantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }
}
