package com.webecommerce.dao.other;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.other.SocialAccountEntity;

public interface ISocialAccountDAO extends GenericDAO <SocialAccountEntity> {
    SocialAccountEntity findByFbID(String fbID);
    SocialAccountEntity findByGgID(String ggID);
}
