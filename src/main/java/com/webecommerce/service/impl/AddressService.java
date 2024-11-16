package com.webecommerce.service.impl;

import com.webecommerce.dao.other.IAddressDAO;
import com.webecommerce.entity.other.AddressEntity;
import com.webecommerce.service.IAddressService;

import javax.inject.Inject;
import java.util.List;

public class AddressService implements IAddressService {

    @Inject
    private IAddressDAO addressDAO;

    @Override
    public AddressEntity updateAddress(AddressEntity address) {
        AddressEntity oldAddress = addressDAO.update(address);
        if (oldAddress == null) {
            return addressDAO.insert(address);
        } else {
            return addressDAO.update(address);
        }
    }

    @Override
    public AddressEntity getAddressById(Long id) {
        return addressDAO.findById(id);
    }

    @Override
    public List<AddressEntity> getAllAddresses() {
        return addressDAO.findAll();
    }

    @Override
    public void deleteAddressById(Long id) {
        addressDAO.delete(id);
    }
}
