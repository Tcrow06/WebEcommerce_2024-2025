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

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    protected EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();
    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Find object by ID
    public T findById(Long id) {
        try {
            return entityManager.find(entityClass, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding object with ID: " + id, e);
            return null;
        }
    }

    // Find all objects
    public List<T> findAll() {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        try {
            return entityManager.createQuery(query, entityClass).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all objects", e);
            return null;
        }
    }

    protected List<T> findAllByQuery(String query) {
        try {
            return entityManager.createQuery(query, entityClass).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all objects", e);
            return null;
        }
    }

    // Find object by attribute
    protected List<T> findByAttribute(String attributeName, Object value) {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e." + attributeName + " = :value";
        try {
            return entityManager.createQuery(query, entityClass)
                    .setParameter("value", value)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding object by attribute: " + attributeName + " with value: " + value, e);
            return null;
        }
    }

    protected T findOneByQuery(String query) {
        try {
            return entityManager.createQuery(query, entityClass).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving single object", e);
            return null;
        }
    }

    // Insert new object
    public T insert(T entity) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            em.persist(entity);  // Insert the object
            em.flush();  // Đảm bảo dữ liệu được ghi vào DB
            em.clear();  // Làm trống bộ nhớ đệm sau khi ghi
            trans.commit();      // Commit the transaction
            LOGGER.log(Level.INFO, "Inserted object: {0}", entity);
            return entity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inserting object", e);
            trans.rollback();  // Rollback if there's an error
            return null;
        } finally {
            em.close();  // Close EntityManager
        }
    }

    // Update object
    public T update(T entity) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            T mergedEntity = em.merge(entity);  // Update the object
            em.flush();
            em.clear();


            trans.commit();

            LOGGER.log(Level.INFO, "Updated object: {0}", mergedEntity);
            return mergedEntity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating object", e);
            trans.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    // Delete object
    public boolean delete(Long id) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);  // Delete the object
                em.flush();
                em.clear();
                trans.commit();
                LOGGER.log(Level.INFO, "Deleted object with ID: {0}", id);
                return true;
            } else {
                LOGGER.log(Level.WARNING, "No object found to delete with ID: {0}", id);
                return false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting object with ID: " + id, e);
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
