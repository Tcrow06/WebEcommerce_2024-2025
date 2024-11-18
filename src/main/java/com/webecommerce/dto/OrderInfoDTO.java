package com.webecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.other.AddressEntity;
import com.webecommerce.entity.people.CustomerEntity;

import javax.persistence.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderInfoDTO {
    private Long id;

    private String recipient;

    private String phone;

    private int isDefault;

    private AddressDTO address;

    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public OrderInfoDTO() {
    }
}
