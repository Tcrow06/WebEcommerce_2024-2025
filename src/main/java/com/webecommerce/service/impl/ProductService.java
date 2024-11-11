package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.paging.Pageable;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class ProductService implements IProductService {

    @Inject
    private ImageServiceImpl imageServiceImpl;

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
        try { // tiến hành lưu ảnh
            imageServiceImpl.setRealPath(product.getRealPathFile());
            imageServiceImpl.setPath(product.getSizeConversionTable());
            imageServiceImpl.saveImageToDisk();
            product.setSizeConversionTableUrl(imageServiceImpl.getId());

            for (ProductVariantDTO productVariant : product.getProductVariants()) {
                imageServiceImpl.setRealPath(product.getRealPathFile());
                imageServiceImpl.setPath(productVariant.getImage());
                imageServiceImpl.saveImageToDisk();
                productVariant.setImageUrl(imageServiceImpl.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;}

        ProductEntity productEntity = productMapper.toEntity(product);
        if (productEntity == null) return null;

        productEntity.setStatus(EnumProductStatus.SELLING);
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

    private ProductDTO getProduct (ProductEntity product) {
        ProductDTO productDTO = productMapper.toDTO(product);
        //lấy discount cho từng sản phâm
        ProductDiscountEntity productDiscountEntity = product.getProductDiscount();
        if (productDiscountEntity != null) {
            if (productDiscountEntity.getEndDate().isAfter(LocalDateTime.now()) && productDiscountEntity.getStartDate().isBefore(LocalDateTime.now())) {
                productDTO.setProductDiscount(
                        productDiscountMapper.toDTO(productDiscountEntity)
                );
            }
        }
        productDTO.setProductVariants(
                productVariantMapper.toDTOList(product.getProductVariants())
        );

        return productDTO;
    }

    // dùng để lấy discout, price mà không cần lay het product variant -> load nhanh hơn
    private List <ProductDTO> getProduct (List<ProductEntity> productEntities) {
        List <ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for (ProductEntity product : productEntities) {
            ProductDTO productDTO = productMapper.toDTO(product);
            //lấy discount cho từng sản phâm
            ProductDiscountEntity productDiscountEntity = product.getProductDiscount();
            if (productDiscountEntity != null) {
                if (productDiscountEntity.getEndDate().isAfter(LocalDateTime.now()) && productDiscountEntity.getStartDate().isBefore(LocalDateTime.now())) {
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
    public List<ProductDTO> findAll(Pageable pageable) {
        List <ProductEntity> products = productDAO.findAll(pageable);
        return getProduct(products);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntities = productDAO.findAll();
        return getProduct(productEntities);
    }

    public List<ProductDTO> findProductsByCategoryCode(String categoryCode) {
        List <ProductEntity> productEntities =  productDAO.findProductsByCategoryCode(categoryCode);
        return getProduct(productEntities);
    }

    public ProductDTO getProductById(Long id) {
        ProductEntity productEntity = productDAO.findById(id);
        return getProduct(productEntity);

//        ProductDTO productDTO = productMapper.toDTO(productEntity);
//        for (ProductVariantEntity productVariant : productEntity.getProductVariants()) {
//            productDTO.getProductVariants().add(
//                    productVariantMapper.toDTO(productVariant)
//            );
//        }

//        return productDTO;
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

    @Override
    public List<ProductDTO> findProductOnSale(int limit) {
        List<ProductEntity>  productEntities =  productDAO.findProductOnSale(limit);
        return getProduct(productEntities);
    }

    @Override
    public List<ProductDTO>  findProductIsNew(int limit) {
        List<ProductEntity>  productEntities =  productDAO.findProductIsNew(limit);
        return getProduct(productEntities);
    }

    @Override
    public List<ProductDTO>  findProductOther(int limit) {
        List<ProductEntity>  productEntities =  productDAO.findProductOther(limit);
        return getProduct(productEntities);
    }

    @Override
    public List<ProductDTO> findProductForAllTag(int limit){
        List<ProductDTO> productOnSales = findProductOnSale(limit);
        List<ProductDTO> productIsNew = findProductIsNew(limit);
        List<ProductDTO> productOther = findProductOther(limit);

        return new ArrayList<>(Stream.of(productOnSales, productIsNew, productOther)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(ProductDTO::getId, product -> product, (existing, replacement) -> existing))
                .values());
    }
    public Long getTotalItem() {
        return productDAO.getTotalItem();
    }
    @Override
    public int setTotalPage(Long totalItem, int maxPageItem) {
        return (int) Math.ceil((double) totalItem / maxPageItem);
    }

    @Override
    public List<String> getAllProductName() {
        return productDAO.getAllProductName();
    }


}
