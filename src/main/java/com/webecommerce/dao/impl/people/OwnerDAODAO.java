package com.webecommerce.dao.impl.people;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.people.IOwnerDAO;
import com.webecommerce.entity.people.OwnerEntity;

public class OwnerDAODAO extends AbstractDAO<OwnerEntity> implements IOwnerDAO {
    public OwnerDAODAO() {
        super(OwnerEntity.class);
    }
}
