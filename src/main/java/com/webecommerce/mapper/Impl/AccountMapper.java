package com.webecommerce.mapper.Impl;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.constant.EnumRoleAccount;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;
import com.webecommerce.mapper.IAccountMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AccountMapper implements IAccountMapper {

    @Override
    public AccountRequest toAccountRequest(AccountEntity accountEntity) {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setUserName(accountEntity.getUsername());
        accountRequest.setPassword(accountEntity.getPassword());
        accountRequest.setStatus(accountEntity.getStatus().toString());
        accountRequest.setRole(accountEntity.getRole().toString());
        return accountRequest;
    }

    @Override
    public AccountEntity toAccountEntity(CustomerRequest customerRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(customerRequest.getUserName());
        accountEntity.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        accountEntity.setRole(EnumRoleAccount.CUSTOMER);
        accountEntity.setStatus(EnumAccountStatus.ACTIVE);
        return accountEntity;
    }
}
