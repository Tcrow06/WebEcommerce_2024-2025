package com.webecommerce.dao.impl.people;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO extends AbstractDAO<CustomerEntity> implements ICustomerDAO {

    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    public CustomerDAO() {
        super(CustomerEntity.class);
    }


    @Override
    public CustomerEntity findByEmail(String email) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        CustomerEntity customer = null;
        try {
            trans.begin();
            String query = "SELECT e FROM CustomerEntity e WHERE e.email = :email";
            customer = em.createQuery(query, CustomerEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            trans.commit();
        }catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No customer found with email: " + email);
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm đối tượng theo email", e);
        } finally {
            em.close();
        }
        return customer;
    }


    public CustomerEntity findById(long id) {
        try {
            String jpql = "SELECT u FROM CustomerEntity u WHERE u.id = :id";
            return entityManager.createQuery(jpql, CustomerEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public int totalCustomers() {
        String query = "SELECT COUNT(p) FROM CustomerEntity p"; // Đếm tổng số sản phẩm
        try {
            Long count = entityManager.createQuery(query, Long.class)
                    .getSingleResult();
            return count != null ? count.intValue() : 0; // Chuyển đổi Long thành int
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tính tổng số sản phẩm", e);
            return 0; // Trả về 0 nếu xảy ra lỗi
        }
    }
}
