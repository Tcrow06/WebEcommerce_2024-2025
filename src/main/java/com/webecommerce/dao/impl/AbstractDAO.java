package com.webecommerce.dao.impl;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    protected EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();

    protected EntityManager getEntityManager() {
        return HibernateUtil.getEmFactory().createEntityManager();
    }

    protected void closeEntityManager(EntityManager em) {
        em.close();
    }

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Find object by ID
    public T findById(Long id) {
        try {
            return getEntityManager().find(entityClass, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding object with ID: " + id, e);
            return null;
        }
    }

    // Find all objects
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        try {
            return em.createQuery(query, entityClass).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all objects", e);
            return null;
        } finally {
            em.close();
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
        // Điều chỉnh câu truy vấn dựa trên việc value có phải null hay không
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE ";
        if (value == null) {
            query += "e." + attributeName + " IS NULL";
        } else {
            query += "e." + attributeName + " = :value";
        }

        try {
            var typedQuery = entityManager.createQuery(query, entityClass);

            // Chỉ đặt tham số nếu value không phải null
            if (value != null) {
                typedQuery.setParameter("value", value);
            }

            return typedQuery.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding object by attribute: " + attributeName + " with value: " + value, e);
            return null;
        }
    }

    // Find object by attribute
    protected List<T> findByAttributeCustom(String attributeName, Object value) {
        // Điều chỉnh câu truy vấn dựa trên việc value có phải null hay không
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE ";
        if (value == null) {
            query += "e." + attributeName + " IS NULL";
        } else {
            query += "e." + attributeName + " = :value";
        }

        EntityManager em = getEntityManager();

        try {
            var typedQuery = em.createQuery(query, entityClass);

            // Chỉ đặt tham số nếu value không phải null
            if (value != null) {
                typedQuery.setParameter("value", value);
            }

            return typedQuery.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding object by attribute: " + attributeName + " with value: " + value, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    // Find object by attribute
    protected T findOneByAttribute(String attributeName, Object value) {
        // Xây dựng câu truy vấn động
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE ";
        if (value == null) {
            query += "e." + attributeName + " IS NULL";
        } else {
            query += "e." + attributeName + " = :value";
        }

        try {
            // Tạo truy vấn động
            var typedQuery = entityManager.createQuery(query, entityClass);

            // Chỉ set tham số nếu giá trị không phải null
            if (value != null) {
                typedQuery.setParameter("value", value);
            }

            // Lấy kết quả duy nhất
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No entity found with attribute: " + attributeName + " and value: " + value);
            return null;
        } catch (NonUniqueResultException e) {
            LOGGER.log(Level.SEVERE, "Multiple entities found for attribute: " + attributeName + " and value: " + value, e);
            throw e; // Nếu cần, bạn có thể ném ngoại lệ ra ngoài để xử lý
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding entity by attribute: " + attributeName + " and value: " + value, e);
            return null;
        }
    }

    protected boolean existsByAttribute(String attributeName, Object value) {
        // Xây dựng câu truy vấn JPQL
        String query = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE ";
        if (value == null) {
            query += "e." + attributeName + " IS NULL";
        } else {
            query += "e." + attributeName + " = :value";
        }

        try {
            var typedQuery = entityManager.createQuery(query, Long.class);

            // Chỉ set tham số nếu giá trị không phải null
            if (value != null) {
                typedQuery.setParameter("value", value);
            }

            // Lấy số lượng và kiểm tra có tồn tại hay không
            Long count = typedQuery.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error checking existence of entity with attribute: " + attributeName + " and value: " + value, e);
            return false;
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
            em.refresh(entity);
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
