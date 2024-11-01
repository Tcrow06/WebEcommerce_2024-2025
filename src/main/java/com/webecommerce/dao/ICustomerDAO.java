package com.webecommerce.dao;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.entity.people.CustomerEntity;

public interface ICustomerDAO {

    CustomerEntity findById(Long id);
    void save(CustomerEntity customerEntity);
}
