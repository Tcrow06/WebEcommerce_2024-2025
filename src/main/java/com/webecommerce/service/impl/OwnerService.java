package com.webecommerce.service.impl;

import com.webecommerce.dao.people.IOwnerDAO;
import com.webecommerce.dto.response.people.OwnerResponse;
import com.webecommerce.entity.people.OwnerEntity;
import com.webecommerce.mapper.IOwnerMapper;
import com.webecommerce.service.IOwnerService;

import javax.inject.Inject;

public class OwnerService implements IOwnerService {

    @Inject
    private IOwnerDAO ownerDAO;
    @Inject
    private IOwnerMapper ownerMapper;
    @Override
    public OwnerResponse findById(Long id) {
        OwnerEntity ownerEntity = ownerDAO.findById(id);
        return ownerMapper.toOwnerResponse(ownerEntity);
    }
}
