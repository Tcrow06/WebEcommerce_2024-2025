package com.webecommerce.service;

import com.webecommerce.dto.response.people.OwnerResponse;

public interface IOwnerService {

    OwnerResponse findById(Long id);
}
