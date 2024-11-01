package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.mapper.ICustomerMapper;
import com.webecommerce.mapper.ISocialAccountMapper;

import javax.inject.Inject;

public class CustomerMapper implements ICustomerMapper {



    @Override
    public CustomerEntity toCustomerEntity(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerRequest.getName());
        return customerEntity;
    }

    @Override
    public CustomerResponse toCustomerResponse(CustomerEntity customerEntity) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customerEntity.getId());
        customerResponse.setName(customerEntity.getName());
        customerResponse.setEmail(customerEntity.getEmail());
        return customerResponse;
    }

    @Override
    public CustomerEntity toCustomerEntity(CustomerResponse customerResponse) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerResponse.getId());
        customerEntity.setName(customerResponse.getName());
        ///////
        return customerEntity;
    }
}
