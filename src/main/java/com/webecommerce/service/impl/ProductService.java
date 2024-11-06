package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.product.ProductDAO;
import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.discount.DiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

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

    @Inject
    private GenericMapper<ProductDiscountDTO, ProductDiscountEntity> productDiscountMapper;

    // Lây danh sách brand có trong product -> load giao diện
    public List<String> getBrands() {
        return productDAO.getBrands() != null ? productDAO.getBrands() : Collections.emptyList();
    }

    @Transactional
    public ProductDTO save(ProductDTO product) {

        ProductEntity productEntity = productMapper.toEntity(product);
        productEntity.setIsNew(LocalDateTime.now());


        productEntity.setCategory(
                categoryDAO.findById(product.getCategory().getId())
        );

        // thiết lập liên kết
        for (ProductVariantEntity productVariant : productEntity.getProductVariants()) {
            productVariant.setProduct(productEntity);
        }

        return productMapper.toDTO(productDAO.insert(productEntity));
    }

    // dùng để lấy discout, price mà không cần lay het product variant -> load nhanh hơn
    private List <ProductDTO> getProduct (List<ProductEntity> productEntities) {
        List <ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for (ProductEntity product : productEntities) {
            ProductDTO productDTO = productMapper.toDTO(product);
            //lấy discount cho từng sản phâm
            ProductDiscountEntity productDiscountEntity = product.getProductDiscount();
            if (productDiscountEntity != null) {
                if (productDiscountEntity.getEndDate().isBefore(LocalDateTime.now())) {
                    productDTO.setProductDiscount(
                            productDiscountMapper.toDTO(productDiscountEntity)
                    );
                }
            }

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

    public List<ProductDTO> findProductsByBrand(String brand) {
        List <ProductEntity> products = productDAO.findProductsByBrand(brand);
        return getProduct(products);
    }

    @Override
    public List<ProductDTO> findAll () {
        List<ProductEntity> productEntities =  productDAO.findAll();
        return getProduct(productEntities);
    }

    public List<ProductDTO> findProductsByCategoryCode(String categoryCode) {
        List <ProductEntity> productEntities =  productDAO.findProductsByCategoryCode(categoryCode);
        return getProduct(productEntities);
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

    public List<String> getListColorBySize (String size, Long productId) {
        List <String> colorList = productDAO.getListColorBySize(size, productId);
        if (colorList != null)
            return colorList;
        return Collections.emptyList();
    }

    public List<String> getListSizeByColor (String color, Long productId) {
        List <String> sizeList = productDAO.getListSizeByColor(color, productId);
        if (sizeList != null)
            return sizeList;
        return Collections.emptyList();
    }

}
