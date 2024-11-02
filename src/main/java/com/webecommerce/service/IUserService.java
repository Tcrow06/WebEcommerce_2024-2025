package com.webecommerce.service;

import com.webecommerce.dto.response.people.UserResponse;

public interface IUserService {
    UserResponse findByID(Long id);
}
