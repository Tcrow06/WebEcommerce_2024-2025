package com.webecommerce.dto.notinentity;

import com.webecommerce.constant.EnumAccountStatus;

public class ManageUserDTO {
    private Long id;
    private String name;
    private String phone;
    private Integer point;
    private Long numOfCancel;
    private EnumAccountStatus status;
    private Integer block;

    public ManageUserDTO(Long id, String name, String phone, Integer point, Long numOfCancel, EnumAccountStatus status, Integer block) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.point = point;
        this.numOfCancel = numOfCancel;
        this.status = status;
        this.block = block;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getNumOfCancel() {
        return numOfCancel;
    }

    public void setNumOfCancel(Long numOfCancel) {
        this.numOfCancel = numOfCancel;
    }

    public EnumAccountStatus getStatus() {
        return status;
    }

    public void setStatus(EnumAccountStatus status) {
        this.status = status;
    }
}
