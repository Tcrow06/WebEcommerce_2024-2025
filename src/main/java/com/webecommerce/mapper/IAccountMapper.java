package com.webecommerce.mapper;

import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.other.AccountResponse;
import com.webecommerce.entity.other.AccountEntity;

public interface IAccountMapper {
    AccountResponse toAccountResponse(AccountEntity accountEntity);
    AccountEntity toAccountEntity(CustomerRequest customerRequest);
}
