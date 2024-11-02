package com.webecommerce.mapper.Impl;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.mapper.GenericMapper;

public class CategoryMapper implements GenericMapper <CategoryDTO, CategoryEntity> {
    @Override
    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setCode(categoryEntity.getCode());
        categoryDTO.setName(categoryEntity.getName());
        return categoryDTO;
    }

    @Override
    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setCode(categoryDTO.getCode());
        categoryEntity.setName(categoryDTO.getName());
        return categoryEntity;
    }
}
