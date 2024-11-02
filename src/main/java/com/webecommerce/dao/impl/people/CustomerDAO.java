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
}
