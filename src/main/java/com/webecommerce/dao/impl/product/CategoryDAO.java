package com.webecommerce.dao.impl.product;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.entity.product.CategoryEntity;

public class CategoryDAO extends AbstractDAO <CategoryEntity> implements ICategoryDAO {
    public CategoryDAO() {
        super(CategoryEntity.class);
    }
}
