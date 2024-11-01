package com.webecommerce.service;

import com.webecommerce.dto.request.other.AccountRequest;

public interface IAccountService {
    AccountRequest findByUserNameAndPasswordAndStatus(String userName, String password, String status);
}
