package com.webecommerce.dao.impl.discount;

import com.webecommerce.context.DBContext;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.DiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDiscountDAO extends AbstractDAO<ProductDiscountEntity> implements IProductDiscountDAO {
    public ProductDiscountDAO() {
        super(ProductDiscountEntity.class);
    }
}