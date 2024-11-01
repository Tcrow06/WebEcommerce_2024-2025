package com.webecommerce.dao.impl.people;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.entity.people.CustomerEntity;

public class CustomerDAO extends AbstractDAO<CustomerEntity> implements ICustomerDAO {

    public CustomerDAO() {
        super(CustomerEntity.class);
    }

    @Override
    public void findByFbID(String fbID) {

    }

    @Override
    public void findByGgID(String ggID) {

    }
}
