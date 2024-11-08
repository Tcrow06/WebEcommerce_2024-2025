package com.webecommerce.dto.discount;

import com.webecommerce.dto.BaseDTO;

import java.time.LocalDateTime;

public abstract class DiscountDTO extends BaseDTO<DiscountDTO> {

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int discountPercentage;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
