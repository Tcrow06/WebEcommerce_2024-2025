package com.webecommerce.service;

import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;

public interface IAccountService {
    AccountRequest findByUserNameAndPasswordAndStatus(String userName, String password, String status);

    CustomerResponse save(CustomerRequest customerRequest);
}
