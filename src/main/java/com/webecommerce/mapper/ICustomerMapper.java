package com.webecommerce.mapper;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.people.UserEntity;

public interface ICustomerMapper {
    CustomerEntity toCustomerEntity(CustomerRequest customerRequest);
    CustomerEntity toCustomerEntityFull(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(CustomerEntity customerEntity);

    CustomerEntity toCustomerEntity (CustomerResponse customerResponse);
}
