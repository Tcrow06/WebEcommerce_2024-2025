package com.webecommerce.service.impl;

import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.IProductService;
import com.webecommerce.utils.HibernateUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    private GenericMapper<ProductDTO, ProductEntity> productMapper;

    @Inject
    private ICategoryDAO categoryDAO;

    @Inject
    private IProductDAO productDAO;

    @Inject
    private IProductVariantDAO productVariantDAO;

    @Override
    public List<ProductEntity> getAllProducts() {
        return productDAO.findAll();
    }

    @Transactional
    public ProductDTO save(ProductDTO product) {

        ProductEntity productEntity = productMapper.toEntity(product);

        productEntity.setCategory(
                categoryDAO.findById(product.getCategory().getId())
        );

        // thiết lập liên e
        for (ProductVariantEntity productVariant : productEntity.getProductVariants()) {
            productVariant.setProduct(productEntity);
        }

        return productMapper.toDTO(productDAO.insert(productEntity));
    }
}
