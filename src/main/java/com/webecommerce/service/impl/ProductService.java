package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.product.ProductDAO;
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
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    private GenericMapper<ProductDTO, ProductEntity> productMapper;

    @Inject
    private GenericMapper<ProductVariantDTO, ProductVariantEntity> productVariantMapper;

    @Inject
    private ICategoryDAO categoryDAO;

    @Inject
    private IProductDAO productDAO;

    @Inject
    private IProductVariantDAO productVariantDAO;


    @Transactional
    public ProductDTO save(ProductDTO product) {

        ProductEntity productEntity = productMapper.toEntity(product);

        productEntity.setCategory(
                categoryDAO.findById(product.getCategory().getId())
        );

        // thiết lập liên kết
        for (ProductVariantEntity productVariant : productEntity.getProductVariants()) {
            productVariant.setProduct(productEntity);
        }

        return productMapper.toDTO(productDAO.insert(productEntity));
    }

    @Override
    public List<ProductDTO> findAll () {
        List <ProductDTO> productDTOS = new ArrayList<ProductDTO>();

        List<ProductEntity> productEntities =  productDAO.findAll();
        for (ProductEntity product : productEntities) {
            ProductDTO productDTO = productMapper.toDTO(product);

            // lấy productvariant để lấy ảnh và giá (lấy product variant rẻ nhất)
            ProductVariantEntity productVariant = productVariantDAO.getProductVariantByProduct(product);
            if (productVariant != null) {
                productDTO.setPhoto(productVariant.getImageUrl());
                productDTO.setPrice(productVariant.getPrice());
            }
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public List<ProductDTO> findProductsByCategoryCode(String categoryCode) {
        List <ProductEntity> productEntities =  productDAO.findProductsByCategoryCode(categoryCode);
        if (productEntities != null) {
            List <ProductDTO> productDTOS = new ArrayList<ProductDTO>();
            for (ProductEntity product : productEntities) {
                ProductDTO productDTO = productMapper.toDTO(product);

                // lấy productvariant để lấy ảnh và giá (lấy product variant rẻ nhất)
                ProductVariantEntity productVariant = productVariantDAO.getProductVariantByProduct(product);
                if (productVariant != null) {
                    productDTO.setPhoto(productVariant.getImageUrl());
                    productDTO.setPrice(productVariant.getPrice());
                }
                productDTOS.add(productDTO);
            }
            return productDTOS;
        } else
            return new ArrayList<>();
    }

    public ProductDTO getProductById(Long id) {
        ProductEntity productEntity = productDAO.findById(id);

        ProductDTO productDTO = productMapper.toDTO(productEntity);
        for (ProductVariantEntity productVariant : productEntity.getProductVariants()) {
            productDTO.getProductVariants().add(
                    productVariantMapper.toDTO(productVariant)
            );
        }

        return productDTO;
    }
}
