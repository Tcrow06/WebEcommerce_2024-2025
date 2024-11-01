package com.webecommerce.service.impl;

import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.mapper.ICustomerMapper;
import com.webecommerce.service.ICustomerService;

import javax.inject.Inject;

public class CustomerService implements ICustomerService {

    @Inject
    private ICustomerDAO customerDAO;
    @Inject
    private ICustomerMapper customerMapper;
    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerRequest.getName());
        customerDAO.insert(customerEntity);
        return customerMapper.toCustomerResponse(customerEntity);
    }

    @Override
    public CustomerResponse findById(Long id) {
        return null;
    }
}
