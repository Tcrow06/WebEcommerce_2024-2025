package com.webecommerce.service.impl;

import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.other.AccountResponse;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.UserResponse;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.exception.DuplicateFieldException;
import com.webecommerce.mapper.IAccountMapper;
import com.webecommerce.mapper.ICustomerMapper;
import com.webecommerce.mapper.Impl.AccountMapper;
import com.webecommerce.service.IAccountService;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class AccountService implements IAccountService {
    @Inject
    private ICustomerMapper customerMapper;
    @Inject
    private IAccountDAO accountDAO;

    @Inject
    private IAccountMapper accountMapper;
    @Override
    public UserResponse findByUserNameAndPasswordAndStatus(String userName, String password, String status) {
        return accountDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
    }

    @Transactional
    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        if (accountDAO.existsByEmail(customerRequest.getEmail())) {
            throw new DuplicateFieldException("email");
        }
        if (accountDAO.existsByPhone(customerRequest.getPhone())) {
            throw new DuplicateFieldException("phone");
        }
        if (accountDAO.existsByUsername(customerRequest.getUserName())) {
            throw new DuplicateFieldException("username");
        }

        CustomerEntity customerEntity = customerMapper.toCustomerEntityFull(customerRequest);
        customerEntity.setCart(new CartEntity());
        AccountEntity accountEntity = accountMapper.toAccountEntity(customerRequest);
        accountEntity.setCustomer(customerEntity);
        accountDAO.insert(accountEntity);
        return customerMapper.toCustomerResponse(accountEntity.getCustomer());
    }

    public AccountResponse findByCustomerId(Long idCustomer) {
        AccountEntity accountEntity = accountDAO.findByCustomerId(idCustomer);
        return accountMapper.toAccountResponse(accountEntity);
    }
}
