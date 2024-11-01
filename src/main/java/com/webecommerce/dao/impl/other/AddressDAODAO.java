package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.IAddressDAO;
import com.webecommerce.entity.other.AddressEntity;

public class AddressDAODAO extends AbstractDAO <AddressEntity> implements IAddressDAO {
    public AddressDAODAO() {
        super(AddressEntity.class);
    }
}
