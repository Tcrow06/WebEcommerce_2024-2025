package com.webecommerce.dao.people;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.people.CustomerEntity;

public interface ICustomerDAO extends GenericDAO<CustomerEntity> {
    CustomerEntity findByEmail(String email);
    CustomerEntity findById(long id);
}
