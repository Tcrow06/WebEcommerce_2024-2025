package com.webecommerce.service;

import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.UserResponse;

public interface IAccountService {
    UserResponse findByUserNameAndPasswordAndStatus(String userName, String password, String status);

    CustomerResponse save(CustomerRequest customerRequest);
    boolean sendOTPToEmail(String email, long id);
    int verifyOTP(String email, String otp);
}
