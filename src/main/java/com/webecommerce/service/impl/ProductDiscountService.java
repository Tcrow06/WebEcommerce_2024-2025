package com.webecommerce.service.impl;

import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.impl.product.ProductVariantDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
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

    @Inject
    ProductVariantDAO productVariantDAO;

    @Transactional
    public ProductDiscountDTO cancelProductDiscount(Long id) {
        ProductDiscountEntity productDiscountEntity = productDiscountDAO.findById(id);
        if (productDiscountEntity != null) {
            if (productDiscountEntity.getEndDate().isAfter(LocalDateTime.now())) {
                productDiscountEntity.setEndDate(LocalDateTime.now().minusMinutes(1));
                return productDiscountMapper.toDTO(
                        productDiscountDAO.update(productDiscountEntity)
                );
            } else throw new IllegalArgumentException("Không thể chỉnh sửa chương trình giảm giá đã kết thúc");
        }
        return null;
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

    @Transactional
    public ProductDiscountDTO update(ProductDiscountDTO productDiscount) {

        ProductDiscountEntity productDiscountEntity = productDiscountDAO.findById(productDiscount.getId());
        if (productDiscountEntity != null) {
            if (productDiscountEntity.getStartDate().isAfter(LocalDateTime.now()) && productDiscountEntity.getEndDate().isAfter(LocalDateTime.now())) { // chỉ chỉnh sửa những discount chưa diễn ra

                productDiscountEntity.setName(productDiscount.getName());
                productDiscountEntity.setStartDate(productDiscount.getStartDate());
                productDiscountEntity.setEndDate(productDiscount.getEndDate());
                productDiscountEntity.setDiscountPercentage(productDiscount.getDiscountPercentage());
                productDiscountEntity.setOutStanding(productDiscount.getIsOutStanding());

                return productDiscountMapper.toDTO(productDiscountDAO.update(productDiscountEntity));
            } else throw new IllegalArgumentException("Mã giảm giá này không cho phép chỉnh sửa !"); // sử dụng khi tham số đầu vào hoặc trạng thái không hợp lệ
        }
        return null;
    }

    @Override
    public ProductDiscountDTO findById(Long id) {
        ProductDiscountEntity productDiscountEntity = productDiscountDAO.findById(id);

        return productDiscountMapper.toDTO(productDiscountEntity);
    }
    @Override
    public List<ProductDiscountDTO> getAllProductDiscount()
    {
        List<ProductDiscountEntity> list = productDiscountDAO.getAllProductDiscount();
        return productDiscountMapper.toDTOList(list);
    }
    @Override
    public List<ProductDiscountDTO> findProductDiscountByProductName(String productName)
    {
        List<ProductDiscountEntity> list = productDiscountDAO.findProductDiscountByProductName(productName);
        return productDiscountMapper.toDTOList(list);
    }
    private List <ProductDiscountDTO> getProductDiscountDTOList(List<ProductDiscountEntity> productDiscountEntities) {
        List<ProductDiscountDTO> productDiscountDTOList = new ArrayList<>();

        for (ProductDiscountEntity productDiscountEntity : productDiscountEntities) {
            if (productDiscountEntity.getEndDate().isBefore(LocalDateTime.now())) {
                continue; // bỏ những product discount đã hết hạn
            }

            ProductDiscountDTO productDiscountDTO = getProductDiscountDTO(productDiscountEntity);

            productDiscountDTOList.add(productDiscountDTO);
        }

        return productDiscountDTOList;
    }

    private ProductDiscountDTO getProductDiscountDTO(ProductDiscountEntity productDiscountEntity) {
        ProductDiscountDTO productDiscountDTO = productDiscountMapper.toDTO(productDiscountEntity);

        ProductEntity productEntity = productDiscountEntity.getProduct();
        if (productEntity != null) {
            ProductVariantEntity productVariant = productVariantDAO.getProductVariantByProduct(productEntity);
            productDiscountDTO.setProduct(
                    productMapper.toDTO(productDiscountEntity.getProduct())
            );

            productDiscountDTO.getProduct().setDiscountPercentage(productDiscountDTO.getDiscountPercentage());

            if (productVariant != null) {
                productDiscountDTO.getProduct().setPhoto(productVariant.getImageUrl());
                productDiscountDTO.getProduct().setPrice(productVariant.getPrice());
            }
        }

        return productDiscountDTO;
    }

    public ProductDiscountDTO findByIdAndHaveProduct(Long id) {
        ProductDiscountEntity productDiscountEntity = productDiscountDAO.findById(id);

        if (productDiscountEntity != null) {
            if (productDiscountEntity.getProduct() != null) {
                return getProductDiscountDTO(productDiscountEntity);
            }
        }

        return null;
    }

    // lấy những discount có sẵn
    public List<ProductDiscountDTO> getProductDiscountValid() {
        List <ProductDiscountEntity> productDiscountEntities = productDiscountDAO.findDiscounthaveProductByDate();

        if (productDiscountEntities == null)
            return new ArrayList<>();

        return getProductDiscountDTOList(productDiscountEntities);
    }

    // lâấy những discout đã hết hạn
    public List <ProductDiscountDTO> getUpcommingProductDiscount () {
        List <ProductDiscountEntity> productDiscountEntities = productDiscountDAO.findDiscounthaveProductByDate(
                LocalDateTime.now().plusHours(1)
        );

        if (productDiscountEntities == null)
            return new ArrayList<>();

        return getProductDiscountDTOList(productDiscountEntities);
    }
    @Override
    public List<ProductDiscountDTO> findProductDiscountByPercent(String percent) {
        return productDiscountMapper.toDTOList(productDiscountDAO.findProductDiscountByPercent(percent));
    }
    @Override
    public List<ProductDiscountDTO> findProductDiscountByTime(LocalDateTime inputTime) {
        return productDiscountMapper.toDTOList(productDiscountDAO.findProductDiscountByTime(inputTime));
    }

}
