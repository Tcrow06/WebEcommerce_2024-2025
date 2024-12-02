package com.webecommerce.service;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.notinentity.RevenueDTO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.paging.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IProductService {
    List<String> getBrands();

    List<ProductDTO> findProductsByBrand(String brand);

    List<ProductDTO> findAll (Pageable pageable);

    List <ProductDTO> getProductsFromDiscount ();

    List<ProductDTO> findProductSelling() ;

    List <ProductDTO> findProductStopSellingByCategoryAndName(String categoryCode, String name);

    List <ProductDTO> findProductSellingByCategoryAndName(String categoryCode, String name);

    // dùng cho controller product admin
    List<ProductDTO> findProductStopSelling() ;

    List<ProductDTO> findAll();

    ProductDTO save(ProductDTO product);

    ProductDTO update(ProductDTO product);

    ProductDTO stopSelling (Long productId);

    List<ProductDTO> findProductsByCategoryCode(String categoryCode);

    ProductDTO getProductById(Long id);

    ProductDTO getProductDetailById (Long id);

    List<String> getListColorBySize (String size, Long productId);

    List<String> getListSizeByColor (String color, Long productId);

    List<ProductDTO> findProductOnSale(int limit);

    List<ProductDTO> findProductIsNew(int limit);

    List<ProductDTO> findProductOther(int limit);

    List<ProductDTO> findProductForAllTag(int limit);

    Long getTotalItem();

    int setTotalPage(Long totalItem, int maxPageItem);

    List<String> getAllProductName();


    List<Map.Entry<ProductDTO, Integer>> findBestSellerProduct(int limit);

    List<Map.Entry<ProductDTO, Integer>>findLowestSellingProducts(int limit);

    int totalProducts();

    Long getTotalItems();

    List<ProductDTO> searchProductsByName(String name);

    List<ProductDTO> findAllByName (Pageable pageable, String name);

    int countByStatus(EnumProductStatus status);

    RevenueDTO getRevenue();

    List<ProductDTO> findProductSuggestion(Long categoryId,int limit, Long productId);
}
