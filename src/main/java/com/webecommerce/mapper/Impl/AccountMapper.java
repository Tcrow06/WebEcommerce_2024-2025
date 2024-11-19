package com.webecommerce.mapper.Impl;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.constant.EnumRoleAccount;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.other.AccountResponse;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;
import com.webecommerce.mapper.IAccountMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AccountMapper implements IAccountMapper {

    @Override
    public AccountResponse toAccountResponse(AccountEntity accountEntity) {
        if(accountEntity == null) return null;
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setUserName(accountEntity.getUsername());
        accountResponse.setPassword(accountEntity.getPassword());
        accountResponse.setStatus(accountEntity.getStatus().toString());
        accountResponse.setRole(accountEntity.getRole().toString());
        return accountResponse;
    }

    @Override
    public AccountEntity toAccountEntity(CustomerRequest customerRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(customerRequest.getUserName());
        accountEntity.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        accountEntity.setRole(EnumRoleAccount.CUSTOMER);
        accountEntity.setStatus(EnumAccountStatus.UNVERIFIED);
        return accountEntity;
    }
}
