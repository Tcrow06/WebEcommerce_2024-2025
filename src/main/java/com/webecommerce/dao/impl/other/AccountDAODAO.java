package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.entity.other.AccountEntity;

public class AccountDAODAO extends AbstractDAO<AccountEntity> implements IAccountDAO {
    public AccountDAODAO() {
        super(AccountEntity.class);
    }
}
