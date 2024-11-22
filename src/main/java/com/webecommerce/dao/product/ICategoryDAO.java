package com.webecommerce.dao.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.product.CategoryEntity;

public interface ICategoryDAO extends GenericDAO <CategoryEntity> {

    CategoryEntity findByCode (String code);

    boolean categoryCodeExists (String code);
}
