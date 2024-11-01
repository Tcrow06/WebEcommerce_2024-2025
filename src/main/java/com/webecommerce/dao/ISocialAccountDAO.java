package com.webecommerce.dao;

import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;

public interface ISocialAccountDAO {
    SocialAccountEntity findByFbID(String fbID);
    SocialAccountEntity findByGgID(String ggID);

    void save(SocialAccountEntity socialAccountEntity);
}
