package com.webecommerce.dto.notinentity;

import com.webecommerce.constant.EnumOrderStatus;

import java.time.LocalDate;

public class DisplayOrderDTO {
    private Long id;
    private String dateTime;
    private Long totalOrder;
    private Long allQuantity;
    private String imgUrl;
    private EnumOrderStatus status;

    public DisplayOrderDTO(Long id, String dateTime, Long totalOrder, Long allQuantity, String imgUrl, EnumOrderStatus status) {
        this.id = id;
        this.dateTime = dateTime;
        this.totalOrder = totalOrder;
        this.allQuantity = allQuantity;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Long totalOrder) {
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
}
