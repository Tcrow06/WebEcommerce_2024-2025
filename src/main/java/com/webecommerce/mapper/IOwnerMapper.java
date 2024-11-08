package com.webecommerce.mapper;

import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.request.people.OwnerRequest;
import com.webecommerce.dto.response.people.OwnerResponse;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.people.OwnerEntity;

public interface IOwnerMapper {
    OwnerResponse toOwnerResponse(OwnerEntity ownerEntity);
}
