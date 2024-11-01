package com.webecommerce.service.impl;

import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.service.IAccountService;

import javax.inject.Inject;

public class AccountService implements IAccountService {

    @Inject
    private IAccountDAO accountDAO;
    @Override
    public AccountRequest findByUserNameAndPasswordAndStatus(String userName, String password, String status) {
        return accountDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
    }
}
