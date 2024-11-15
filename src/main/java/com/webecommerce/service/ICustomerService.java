package com.webecommerce.service;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.UserResponse;

public interface ICustomerService {

    CustomerResponse  save(CustomerRequest customerRequest);


    CustomerResponse findById(Long id);
    CustomerResponse findByEmail(String email);

}
