package com.webecommerce.service.impl;

import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.ICategoryService;
import javax.persistence.EntityExistsException;
import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryDAO categoryDAO;

    @Inject
    private GenericMapper <CategoryDTO, CategoryEntity> categoryMapper;

    public List <CategoryDTO> findAll() {
        List <CategoryEntity> categoryEntitiesList = categoryDAO.findAll();
        return
                categoryMapper.toDTOList(categoryEntitiesList);
    }

    public CategoryDTO save (CategoryDTO categoryDTO) throws Exception {

        if (categoryDAO.categoryCodeExists(categoryDTO.getCode()))
            throw new EntityExistsException("Mã category đã tồn tại: " + categoryDTO.getCode());


        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);

        return categoryMapper.toDTO(
                categoryDAO.insert(categoryEntity)
        );
    }

    public boolean delete (Long id) {
        return categoryDAO.delete(id);
    }

    public void delete (Long [] ids) {
        for (Long id : ids) {
            delete(id);
        }
    }
}
