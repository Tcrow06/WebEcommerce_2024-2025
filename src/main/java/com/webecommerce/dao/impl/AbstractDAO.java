package com.webecommerce.dao.impl;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();
    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Lấy đối tượng qua ID
    public T findById(Long id) {
        try {
            return entityManager.find(entityClass, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm đối tượng với ID: " + id, e);
            return null;
        }
    }

    // Lấy tất cả các đối tượng
    public List<T> findAll() {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        try {
            return entityManager.createQuery(query, entityClass).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy tất cả các đối tượng", e);
            return null;
        }
    }

    // Thêm đối tượng mới
    public T insert(T entity) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            em.persist(entity);  // Thực hiện thêm đối tượng
            trans.commit();      // Commit giao dịch

            LOGGER.log(Level.INFO, "Đã thêm đối tượng: {0}", entity);
            return entity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi thêm đối tượng", e);
            trans.rollback();  // Rollback nếu có lỗi
            return null;
        } finally {
            em.close();  // Đóng EntityManager
        }
    }

    // Cập nhật đối tượng
    public T update(T entity) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            T mergedEntity = em.merge(entity);  // Cập nhật đối tượng
            trans.commit();

            LOGGER.log(Level.INFO, "Đã cập nhật đối tượng: {0}", mergedEntity);
            return mergedEntity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật đối tượng", e);
            trans.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    // Xóa đối tượng
    public boolean delete(Long id) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);  // Xóa đối tượng
                trans.commit();
                LOGGER.log(Level.INFO, "Đã xóa đối tượng với ID: {0}", id);
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Không tìm thấy đối tượng để xóa với ID: {0}", id);
                return false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa đối tượng với ID: " + id, e);
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
