package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.impl.review.ProductReviewDAO;
import com.webecommerce.dao.product.ICategoryDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dao.review.IProductReviewDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductReviewDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.dto.notinentity.RevenueDTO;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.mapper.Impl.CategoryMapper;
import com.webecommerce.paging.Pageable;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
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

    @Inject
    private CategoryMapper categoryMapper;

    @Inject
    private IProductReviewDAO productReviewDAO;

    // Lây danh sách brand có trong product -> load giao diện
    public List<String> getBrands() {
        return productDAO.getBrands() != null ? productDAO.getBrands() : Collections.emptyList();
    }

    private void dtoToEntity(ProductDTO a, ProductEntity b) {
        b.setName(a.getName());
        b.setDescription(a.getDescription());
        b.setBrand(a.getBrand());
        b.setCategory(categoryDAO.findById(a.getCategory().getId()));

        if (a.getSizeConversionTableUrl() != null && !a.getSizeConversionTableUrl().isEmpty()) {
            b.setSizeConversionTableUrl(a.getSizeConversionTableUrl());
        }

        Map<Long, ProductVariantEntity> variantMap = b.getProductVariants().stream()
                .collect(Collectors.toMap(ProductVariantEntity::getId, v -> v));

        for (Map.Entry<Long, ProductVariantEntity> entry : variantMap.entrySet()) {entry.getValue().setStatus(EnumProductStatus.STOP_SELLING);}

        for (ProductVariantDTO productVariantDTO : a.getProductVariants()) {
            ProductVariantEntity productVariantEntity = null;
            if (productVariantDTO.getId() == null) {
                // Thêm variant mới
                productVariantEntity = productVariantMapper.toEntity(productVariantDTO);
                b.getProductVariants().add(productVariantEntity);
                productVariantEntity.setProduct(b);
            } else {
                // Cập nhật variant đã tồn tại
                productVariantEntity = variantMap.get(productVariantDTO.getId());
                if (productVariantEntity != null) {
                    productVariantEntity.setPrice(productVariantDTO.getPrice());
                    productVariantEntity.setSize(productVariantDTO.getSize().toUpperCase());
                    productVariantEntity.setColor(productVariantDTO.getColor().toUpperCase());
                    productVariantEntity.setQuantity(productVariantDTO.getQuantity());
                    if (productVariantDTO.getImageUrl() != null && !productVariantDTO.getImageUrl().isEmpty()) {
                        productVariantEntity.setImageUrl(productVariantDTO.getImageUrl());
                    }
                }
            }
            productVariantEntity.setStatus(EnumProductStatus.SELLING);
        }
    }


    @Transactional
    public ProductDTO update(ProductDTO product) {

        ProductEntity productEntity = productDAO.findById(product.getId());
        if (productEntity == null) return null;

        try { // tiến hành lưu ảnh

            if (product.getSizeConversionTable() != null) {
                imageServiceImpl.setRealPath(product.getRealPathFile());
                imageServiceImpl.setPath(product.getSizeConversionTable());
                imageServiceImpl.saveImageToDisk();
                product.setSizeConversionTableUrl(imageServiceImpl.getId());
            }

            for (ProductVariantDTO productVariant : product.getProductVariants()) {
                if (productVariant.getImage() != null) {
                    imageServiceImpl.setRealPath(product.getRealPathFile());
                    imageServiceImpl.setPath(productVariant.getImage());
                    imageServiceImpl.saveImageToDisk();
                    productVariant.setImageUrl(imageServiceImpl.getId());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        dtoToEntity(product, productEntity);

        return productMapper.toDTO(productDAO.update(productEntity));
    }

    @Transactional
    public ProductDTO stopSelling (Long productId) {

        ProductEntity productEntity = productDAO.findById(productId);
        if (productEntity == null) return null;

        productEntity.setStatus(EnumProductStatus.STOP_SELLING);

        return productMapper.toDTO(productDAO.update(productEntity));
    }

    @Transactional
    public ProductDTO save(ProductDTO product) {
        try { // tiến hành lưu ảnh
            if (product.getSizeConversionTable() != null) {
                imageServiceImpl.setRealPath(product.getRealPathFile());
                imageServiceImpl.setPath(product.getSizeConversionTable());
                imageServiceImpl.saveImageToDisk();
                product.setSizeConversionTableUrl(imageServiceImpl.getId());
            }

            for (ProductVariantDTO productVariant : product.getProductVariants()) {
                imageServiceImpl.setRealPath(product.getRealPathFile());
                imageServiceImpl.setPath(productVariant.getImage());
                imageServiceImpl.saveImageToDisk();
                productVariant.setImageUrl(imageServiceImpl.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
            productVariant.setStatus(EnumProductStatus.SELLING);
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

        // lấy số sao
        productDTO.setAverageStars(productReviewDAO.calculateStarByProduct(product.getId()));
        productDTO.setCountProductReview(productReviewDAO.countProductReviewByProduct(product.getId()));

        productDTO.setProductVariants(
                productVariantMapper.toDTOList(
                        productVariantDAO.getProductVariantsByProduct(product)
                )
        );

        return productDTO;
    }
    // -----------------------------

    // dùng hàm này thì hỏi lại t nha @phamtienanh
    private List <ProductDTO> getProductsWithDiscount (List<ProductEntity> productEntities) {
        List <ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for (ProductEntity product : productEntities) {
            ProductDTO productDTO = productMapper.toDTO(product);
            //lấy discount cho từng sản phâm
            ProductDiscountEntity productDiscountEntity = product.getProductDiscount();
            if (productDiscountEntity != null) {
                if (productDiscountEntity.getEndDate().isAfter(LocalDateTime.now())) {
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

    // dùng cho controller productdiscount @phamtienanh
    public List <ProductDTO> getProductsFromDiscount () {
        List <ProductEntity> products = productDAO.findProductByStatus(EnumProductStatus.SELLING);

        if (products == null) return new ArrayList<>();
        return getProductsWithDiscount(products);
    }

    // dùng cho controller product admin
    public List<ProductDTO> findProductSelling() {
        List <ProductEntity> productEntities = productDAO.findProductByStatus(EnumProductStatus.SELLING);
        if (productEntities == null) return new ArrayList<>();

        return getProductsWithDiscount(productEntities);
    }

    public List <ProductDTO> findProductSellingByCategoryAndName(String categoryCode, String name) {
        List <ProductEntity> productEntities = productDAO.findProductByCategoryOrStatusOrName(categoryCode,EnumProductStatus.SELLING,name);
        if (productEntities == null) return new ArrayList<>();

        return getProductsWithDiscount(productEntities);
    }

    public List <ProductDTO> findProductStopSellingByCategoryAndName(String categoryCode, String name) {
        List <ProductEntity> productEntities = productDAO.findProductByCategoryOrStatusOrName(categoryCode,EnumProductStatus.STOP_SELLING,name);
        if (productEntities == null) return new ArrayList<>();

        return getProductsWithDiscount(productEntities);
    }

    // dùng cho controller product admin
    public List<ProductDTO> findProductStopSelling() {
        List <ProductEntity> productEntities = productDAO.findProductByStatus(EnumProductStatus.STOP_SELLING);
        if (productEntities == null) return new ArrayList<>();

        return getProductsWithDiscount(productEntities);
    }

    //------------------------------------------------------------------

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
            // lấy đánh giá
            productDTO.setAverageStars(
                    productReviewDAO.calculateStarByProduct(product.getId())
            );

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
        if (productEntity != null)
            return getProduct(productEntity);
        return null;
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

    @Override
    public Long getTotalItems() {
        return productDAO.getTotalItems();
    }

    @Override
    public List<Map.Entry<ProductDTO, Integer>> findBestSellerProduct(int limit){
        List<Object[]> results = productDAO.findBestSellerProduct(limit);
        List<Map.Entry<ProductDTO, Integer>> list = new ArrayList<>();
        for(Object[] result : results){
            ProductEntity product = (ProductEntity)result[0];
            ProductDTO productDTO = productMapper.toDTO(product);
            ProductVariantEntity productVariant = productVariantDAO.getProductVariantByProduct(product);
            if (productVariant != null) {
                productDTO.setPhoto(productVariant.getImageUrl());
                productDTO.setPrice(productVariant.getPrice());
            }
            CategoryEntity categoryEntity = categoryDAO.findById(product.getCategory().getId());
            if(categoryEntity!=null){
                productDTO.setCategory(categoryMapper.toDTO(categoryEntity));
            }
            Integer sales = ((Long) result[1]).intValue();;
            Map.Entry<ProductDTO, Integer> entry = new AbstractMap.SimpleEntry<>(productDTO, sales);
            list.add(entry);
        }
        return list;
    }

    @Override
    public List<Map.Entry<ProductDTO, Integer>> findLowestSellingProducts(int limit) {
        List<Object[]> results = productDAO.findLowestSellingProducts(limit);
        List<Map.Entry<ProductDTO, Integer>> list = new ArrayList<>();
        for(Object[] result : results){
            ProductEntity product = (ProductEntity)result[0];
            ProductDTO productDTO = productMapper.toDTO(product);
            ProductVariantEntity productVariant = productVariantDAO.getProductVariantByProduct(product);
            if (productVariant != null) {
                productDTO.setPhoto(productVariant.getImageUrl());
                productDTO.setPrice(productVariant.getPrice());
            }
            CategoryEntity categoryEntity = categoryDAO.findById(product.getCategory().getId());
            if(categoryEntity!=null){
                productDTO.setCategory(categoryMapper.toDTO(categoryEntity));
            }
            Integer sales = ((Long) result[1]).intValue();;
            Map.Entry<ProductDTO, Integer> entry = new AbstractMap.SimpleEntry<>(productDTO, sales);
            list.add(entry);
        }
        return list;
    }

    public int totalProducts(){
        return productDAO.totalProducts();
    }


    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        List <ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        List<ProductEntity> products = productDAO.searchProductsByName(name);
        if (products != null) {
            for (ProductEntity product : products) {
                ProductDTO productDTO = productMapper.toDTO(product);
                ProductVariantEntity productVariant = productVariantDAO.getProductVariantByProduct(product);
                if (productVariant != null) {
                    productDTO.setPrice(productVariant.getPrice());
                }
                productDTOS.add(productDTO);
            }
        }
        return productDTOS;
//        List<ProductEntity> products = productDAO.searchProductsByName(name);
//        return products.stream()
//                .map(productMapper::toDTO)
//                .collect(Collectors.toList());
    }

    @Override
    public int countByStatus(EnumProductStatus status) {
        return productDAO.countByStatus(status);
    }
    @Override
    public RevenueDTO getRevenue() {
        return productDAO.getRevenue();
    }

    @Override
    public List<ProductDTO> findAllByName(Pageable pageable, String name) {
        List <ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        List<ProductEntity> products = productDAO.findAllByName(pageable, name);
        if (products != null) {
            for (ProductEntity product : products) {
                ProductDTO productDTO = productMapper.toDTO(product);
                ProductVariantEntity productVariant = productVariantDAO.getProductVariantByProduct(product);
                if (productVariant != null) {
                    productDTO.setPhoto(productVariant.getImageUrl());
                    productDTO.setPrice(productVariant.getPrice());
                }
                productDTOS.add(productDTO);
            }
        }
        return productDTOS;
    }

}
