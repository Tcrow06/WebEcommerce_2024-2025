package com.webecommerce.dao.discount;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IProductDiscountDAO extends GenericDAO<ProductDiscountEntity> {
    List<ProductDiscountEntity> findDiscounNotProduct();
    List<ProductDiscountEntity> findDiscounthaveProductByDate (LocalDateTime start);
    List<ProductDiscountEntity> findDiscounthaveProductByDate();

    int countDiscountValid();
}