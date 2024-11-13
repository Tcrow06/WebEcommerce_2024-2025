package com.webecommerce.service.impl;

import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.IProductDiscountService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDiscountService implements IProductDiscountService {
    @Inject
    IProductDiscountDAO productDiscountDAO;

    @Inject
    IProductDAO productDAO;

    @Inject
    GenericMapper <ProductDiscountDTO, ProductDiscountEntity> productDiscountMapper;

    @Inject
    GenericMapper <ProductDTO, ProductEntity> productMapper;

    @Transactional
    public ProductDiscountDTO cancelProductDiscount(Long id) {
        ProductDiscountEntity productDiscountEntity = productDiscountDAO.findById(id);
        productDiscountEntity.setEndDate(LocalDateTime.now().minusMinutes(1));
        return productDiscountMapper.toDTO(
                productDiscountDAO.update(productDiscountEntity)
        );
    }


    @Transactional
    public ProductDiscountDTO save (ProductDiscountDTO productDiscount) {

        ProductDiscountEntity productDiscountEntity = productDiscountMapper.toEntity(productDiscount);

        ProductEntity productEntity = productDAO.findById(productDiscount.getProduct().getId());

        if (productEntity != null) {
            // Thiết lập liên kết giữa sản phẩm và discount
            productDiscountEntity.setProduct(productEntity);
            productEntity.setProductDiscount(productDiscountEntity); // Cập nhật liên kết hai chiều

            productEntity = productDAO.update(productEntity); // Cập nhật sản phẩm

            return productDiscountMapper.toDTO(productEntity.getProductDiscount());
        }

        return null;
    }

    @Override
    public ProductDiscountDTO findById(Long id) {
        ProductDiscountEntity productDiscountEntity = productDiscountDAO.findById(id);

        return productDiscountMapper.toDTO(productDiscountEntity);

    private List <ProductDiscountDTO> getProductDiscountDTOList(List<ProductDiscountEntity> productDiscountEntities) {
        List <ProductDiscountDTO> productDiscountDTOList = new ArrayList<>();

        for (ProductDiscountEntity productDiscountEntity : productDiscountEntities) {
            ProductDiscountDTO productDiscountDTO = productDiscountMapper.toDTO(productDiscountEntity);
            productDiscountDTO.setProduct(
                    productMapper.toDTO(productDiscountEntity.getProduct())
            );
            productDiscountDTOList.add(productDiscountDTO);
        }

        return productDiscountDTOList;
    }

    // lấy những discount có sẵn
    public List<ProductDiscountDTO> getProductDiscountValid() {
        List <ProductDiscountEntity> productDiscountEntities = productDiscountDAO.findDiscountByDate(LocalDateTime.now());

        if (productDiscountEntities == null)
            return new ArrayList<>();

        return getProductDiscountDTOList(productDiscountEntities);
    }

    // lâấy những discout đã hết hạn
    public List <ProductDiscountDTO> getExpiredProductDiscount () {
        List <ProductDiscountEntity> productDiscountEntities = productDiscountDAO.findDiscountByDate(LocalDateTime.MIN, LocalDateTime.now().minusMinutes(1));

        if (productDiscountEntities == null)
            return new ArrayList<>();

        return getProductDiscountDTOList(productDiscountEntities);
    }

    // lấy những discount sắp diễn ra
    public List <ProductDiscountDTO> getCommingProductDiscount () {
        List <ProductDiscountEntity> productDiscountEntities = productDiscountDAO.findDiscountByDate(LocalDateTime.now().plusMinutes(1), LocalDateTime.MAX);

        if (productDiscountEntities == null)
            return new ArrayList<>();

        return getProductDiscountDTOList(productDiscountEntities);
    }
}
