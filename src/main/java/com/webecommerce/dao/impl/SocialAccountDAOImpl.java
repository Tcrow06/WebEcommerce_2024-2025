package com.webecommerce.dao.impl;

import com.webecommerce.dao.ISocialAccountDAO;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

import java.util.List;

public class SocialAccountDAOImpl implements ISocialAccountDAO {

    @Override
    public SocialAccountEntity findByFbID(String fbID) {
        return null;
    }

    @Override
    public SocialAccountEntity findByGgID(String ggID) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();

        SocialAccountEntity result = null;
        try {
            String jpql = "SELECT s FROM  SocialAccountEntity  s WHERE s.ggID  = :ggID";
            TypedQuery<SocialAccountEntity> query = em.createQuery(jpql, SocialAccountEntity.class);
            query.setParameter("ggID", ggID);
            result = query.getSingleResult();
        } catch (NoResultException e) {
            // Không tìm thấy kết quả, trả về null hoặc xử lý theo cách phù hợp
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public void save(SocialAccountEntity socialAccountEntity) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(socialAccountEntity);
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
