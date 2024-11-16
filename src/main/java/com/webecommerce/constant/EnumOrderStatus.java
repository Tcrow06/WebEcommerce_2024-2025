package com.webecommerce.constant;

public enum EnumOrderStatus {
    PENDING("Chờ xác nhận"),
    DELIVERED("Đã vận chuyển"),
    WAITING("Chờ xử lý"),
    PROCESSED("Đã xử lý"),
    RECEIVED("Đã nhận"),
    CANCELLED("Đã hủy");

    private String description;

    EnumOrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}