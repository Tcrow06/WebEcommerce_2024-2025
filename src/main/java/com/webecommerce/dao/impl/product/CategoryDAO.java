package com.webecommerce.dao.impl.product;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.entity.product.CategoryEntity;

public class CategoryDAO extends AbstractDAO <CategoryEntity> implements ICategoryDAO {
    public CategoryDAO() {
        super(CategoryEntity.class);
    }

    public CategoryEntity findByCode (String code) {
        return super.findOneByAttribute("code",code);
    }

    public boolean categoryCodeExists (String code) {
        return super.existsByAttribute("code",code);
    }
}
