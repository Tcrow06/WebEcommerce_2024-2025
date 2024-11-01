package com.webecommerce.mapper;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;

public interface ISocialAccountMapper {
    SocialAccountEntity toSocialAccountEntity(CustomerRequest customerRequest);
}
