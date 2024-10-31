package com.webecommerce.dao;

public interface ICustomerDAO {
    void findByFbID(String fbID);
    void findByGgID(String ggID);
}
