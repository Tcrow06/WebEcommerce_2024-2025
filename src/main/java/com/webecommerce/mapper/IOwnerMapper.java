package com.webecommerce.mapper;

import com.webecommerce.dto.response.people.OwnerResponse;
import com.webecommerce.entity.people.OwnerEntity;

public interface IOwnerMapper {
    OwnerResponse toOwnerResponse(OwnerEntity ownerEntity);
}
