package com.webecommerce.service;

import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.dto.CategoryDTO;

import javax.inject.Inject;
import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO save (CategoryDTO categoryDTO) throws Exception;
    boolean delete (Long id);
    void delete (Long [] ids);
}
