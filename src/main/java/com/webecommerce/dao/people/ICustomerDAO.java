package com.webecommerce.dao.people;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.people.CustomerEntity;

public interface ICustomerDAO extends GenericDAO<CustomerEntity> {
    void findByFbID(String fbID);
    void findByGgID(String ggID);
}
