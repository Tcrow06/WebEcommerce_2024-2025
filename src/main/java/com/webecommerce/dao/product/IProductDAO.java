package com.webecommerce.dao.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import java.util.List;

public interface IProductDAO extends GenericDAO <ProductEntity> {
    List<ProductEntity> findProductsByCategoryCode(String categoryCode);
    List<String> getListColorBySize (String size, Long productId);
    List<String> getListSizeByColor (String color, Long productId);
    List <String> getBrands ();
    List<ProductEntity> findProductsByBrand(String brand);
}
