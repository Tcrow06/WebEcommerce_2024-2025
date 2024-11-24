package com.webecommerce.dao.other;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.response.other.AccountResponse;
import com.webecommerce.dto.response.people.UserResponse;
import com.webecommerce.entity.other.AccountEntity;

public interface IAccountDAO extends GenericDAO <AccountEntity> {
    UserResponse findByUserNameAndPasswordAndStatus(String userName, String password, String status);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);
    AccountEntity findByCustomerId(Long customerId);


    UserResponse findByUserNameAndPassword(String userName, String password);

}
