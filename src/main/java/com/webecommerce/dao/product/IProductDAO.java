package com.webecommerce.dao.product;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.paging.Pageable;

import java.util.List;
import java.util.Map;

public interface IProductDAO extends GenericDAO <ProductEntity> {
    List<ProductEntity> findProductsByCategoryCode(String categoryCode);

    List<String> getListColorBySize (String size, Long productId);

    List<String> getListSizeByColor (String color, Long productId);

    List <String> getBrands ();

    List<ProductEntity> findProductsByBrand(String brand);

    List<ProductEntity> findProductOnSale(int limit);

    List<ProductEntity> findProductIsNew(int limit);

    List<ProductEntity> findProductOther(int limit);

    Long getTotalItem();

    List<ProductEntity> findAll(Pageable pageable);

    List<String> getAllProductName();


    List<Object[]> findBestSellerProduct(int limit);
    List<Object[]>findLowestSellingProducts(int limit);

    int totalProducts();

    List<ProductEntity> findProductByStatus(EnumProductStatus status);

    Long getTotalItems();
    List<ProductEntity> searchProductsByName(String name);

    int countByStatus(EnumProductStatus status);
}
