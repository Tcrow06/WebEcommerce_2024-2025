package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.ISocialAccountDAO;
import com.webecommerce.entity.other.SocialAccountEntity;

public class SocialAccountDAODAO extends AbstractDAO<SocialAccountEntity> implements ISocialAccountDAO {
    public SocialAccountDAODAO() {
        super(SocialAccountEntity.class);
    }
}
