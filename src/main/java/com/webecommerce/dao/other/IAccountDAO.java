package com.webecommerce.dao.other;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.entity.other.AccountEntity;

public interface IAccountDAO extends GenericDAO <AccountEntity> {
    AccountRequest findByUserNameAndPasswordAndStatus(String userName, String password, String status);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);
}
