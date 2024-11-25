package com.webecommerce.dto.notinentity;

import com.webecommerce.constant.EnumAccountStatus;

public class UpdateStatusAccountDTO {
    private Long userId;
    private EnumAccountStatus status;

    public UpdateStatusAccountDTO(Long userId, EnumAccountStatus status) {
        this.userId = userId;
        this.status = status;
    }

    public UpdateStatusAccountDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EnumAccountStatus getStatus() {
        return status;
    }

    public void setStatus(EnumAccountStatus status) {
        this.status = status;
    }
}
