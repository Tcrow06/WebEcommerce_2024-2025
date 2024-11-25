package com.webecommerce.mapper.Impl;

import com.webecommerce.constant.EnumRole;
import com.webecommerce.dto.request.people.OwnerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.OwnerResponse;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.people.OwnerEntity;
import com.webecommerce.mapper.IOwnerMapper;

public class OwnerMapper implements IOwnerMapper {
    @Override
    public OwnerResponse toOwnerResponse(OwnerEntity ownerEntity) {
        OwnerResponse ownerResponse = new OwnerResponse();
        if(ownerEntity ==null){
            return null;
        }
        ownerResponse.setId(ownerEntity.getId());
        ownerResponse.setName(ownerEntity.getName());
        ownerResponse.setPhone(ownerResponse.getPhone());
        ownerResponse.setEmail(ownerResponse.getEmail());
        ownerResponse.setAvatar(ownerResponse.getAvatar());
        ownerResponse.setRole(EnumRole.OWNER);
        return ownerResponse;
    }

}
