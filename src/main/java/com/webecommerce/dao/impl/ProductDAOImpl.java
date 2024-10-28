package com.webecommerce.dao.impl;

import com.webecommerce.dao.IProductDAO;
import com.webecommerce.entity.ProductEntity;
import com.webecommerce.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {


//    private static final Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);
//
//    @Override
//    public List<ProductEntity> getAllProduct() {
//        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
//        List<ProductEntity> productList = null;
//
//        try {
//            TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class);
//            productList = query.getResultList();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            em.close();
//        }
//
//        return productList;
//    }
}
