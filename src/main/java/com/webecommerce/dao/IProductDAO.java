package com.webecommerce.dao;


import com.webecommerce.entity.Product;

public interface IProductDAO {
    Product getProductById(Long id);
    void insert(Product product);

    void update(Product product);

}
