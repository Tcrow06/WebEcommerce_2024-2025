package com.webecommerce.dao.impl;

import com.webecommerce.dao.ICustomerDAO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;

public class CustomerDAOImpl implements ICustomerDAO {
    EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
    @Override
    public CustomerEntity findById(Long id) {
        try {
            return em.find(CustomerEntity.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void save(CustomerEntity customerEntity) {
        try {
            em.getTransaction().begin();
            em.persist(customerEntity);
            em.getTransaction().commit();
        }catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
