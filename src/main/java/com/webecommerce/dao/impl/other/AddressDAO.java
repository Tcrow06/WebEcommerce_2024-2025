package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.IAddressDAO;
import com.webecommerce.entity.other.AddressEntity;

public class AddressDAO extends AbstractDAO <AddressEntity> implements IAddressDAO {
    public AddressDAO() {
        super(AddressEntity.class);
    }
}