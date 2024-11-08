package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.mapper.ICustomerMapper;

public class CustomerMapper implements ICustomerMapper{



    @Override
    public CustomerEntity toCustomerEntity(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerRequest.getName());
        customerEntity.setEmail(customerRequest.getEmail());
        return customerEntity;
    }

    @Override
    public CustomerEntity toCustomerEntityFull(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerRequest.getName());
        customerEntity.setPhone(customerRequest.getPhone());
        customerEntity.setEmail(customerRequest.getEmail());
        return customerEntity;
    }

    @Override
    public CustomerResponse toCustomerResponse(CustomerEntity customerEntity) {
        CustomerResponse customerResponse = new CustomerResponse();
        if(customerEntity==null)
            return null;
        customerResponse.setId(customerEntity.getId());
        customerResponse.setName(customerEntity.getName());
        customerResponse.setEmail(customerEntity.getEmail());
        customerResponse.setRole("CUSTOMER");
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
