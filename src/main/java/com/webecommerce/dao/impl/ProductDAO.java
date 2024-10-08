package com.webecommerce.dao.impl;

import com.webecommerce.dao.IProductDAO;
import com.webecommerce.utils.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProductDAO implements IProductDAO {

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void insert(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(product);
            trans.commit();

        }catch (Exception e){
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {

    }
}
