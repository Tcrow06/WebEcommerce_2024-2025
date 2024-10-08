package com.webecommerce.dao;


public interface IProductDAO {
    Product getProductById(Long id);
    void insert(Product product);

    void update(Product product);

}
