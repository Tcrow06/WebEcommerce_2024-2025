package com.webecommerce.service;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;

public interface ISocialAccountService {
    CustomerResponse findByFbID(String fbID);
    CustomerResponse findByGgID(String ggID);

    CustomerResponse save(CustomerRequest customerRequest);
    CustomerResponse saveSocialByEmail(CustomerRequest customerRequest);

    CustomerResponse findAccount(CustomerRequest customerRequest, String provider);
}
