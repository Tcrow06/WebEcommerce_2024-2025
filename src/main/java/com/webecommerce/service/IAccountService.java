package com.webecommerce.service;

import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.other.AccountResponse;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.UserResponse;

public interface IAccountService {
    UserResponse findByUserNameAndPasswordAndStatus(String userName, String password, String status);
    AccountResponse findByCustomerId(Long customerId);
    CustomerResponse save(CustomerRequest customerRequest);

    void setPassword(long id, String password);
    boolean existsUsernameAndEmail(String username, String email);
    boolean sendOTPToEmail(String email, long id, String purpose);
    int verifyOTP(String email, String otp);

    String checkLogin(AccountRequest accountRequest);







    UserResponse findByUserNameAndPassword(String userName, String password);
}
