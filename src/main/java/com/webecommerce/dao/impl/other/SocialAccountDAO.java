package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.ISocialAccountDAO;
import com.webecommerce.entity.other.SocialAccountEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class SocialAccountDAO extends AbstractDAO<SocialAccountEntity> implements ISocialAccountDAO {
    public SocialAccountDAO() {
        super(SocialAccountEntity.class);
    }
    @Override
    public SocialAccountEntity findByFbID(String fbID) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        SocialAccountEntity result = null;
        try {
            String jpql = "SELECT s FROM  SocialAccountEntity  s WHERE s.fbID  = :fbID";
            TypedQuery<SocialAccountEntity> query = em.createQuery(jpql, SocialAccountEntity.class);
            query.setParameter("fbID", fbID);
            result = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
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
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }
}
