package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.entity.other.SocialAccountEntity;
import com.webecommerce.mapper.ISocialAccountMapper;

public class SocialAccountMapper implements ISocialAccountMapper {
    @Override
    public SocialAccountEntity toSocialAccountEntity(CustomerRequest customerRequest) {
        SocialAccountEntity socialAccountEntity = new SocialAccountEntity();
        socialAccountEntity.setGgID(customerRequest.getGgID());
        socialAccountEntity.setFbID(customerRequest.getFbID());
        return socialAccountEntity;
    }
}
