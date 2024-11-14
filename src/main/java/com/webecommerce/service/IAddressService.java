package com.webecommerce.service;

import com.webecommerce.entity.other.AddressEntity;

import java.util.List;

public interface IAddressService {

    AddressEntity updateAddress(AddressEntity address);

    AddressEntity getAddressById(Long id);

    List<AddressEntity> getAllAddresses();

    void deleteAddressById(Long id);
}
