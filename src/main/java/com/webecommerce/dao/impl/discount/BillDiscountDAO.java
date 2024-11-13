package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;

import java.util.List;
import java.util.logging.Level;

public class BillDiscountDAO extends AbstractDAO<BillDiscountEntity> implements IBillDiscountDAO {

    public BillDiscountDAO() {
        super(BillDiscountEntity.class);
    }
    public List<BillDiscountEntity> getAllDiscountEligible(Long idUser) {
        String query = "SELECT b FROM BillDiscountEntity b, CustomerEntity c " +
                "WHERE b.minimumInvoiceAmount < c.loyaltyPoint " +
                "AND c.id = :idUser " +
                "AND CURRENT_TIMESTAMP BETWEEN b.startDate AND b.endDate";
        try {
            return entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("idUser", idUser)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy bill có minimumInvoiceAmount < loyaltyPoint của khách hàng với ID: " + idUser, e);
            return null;
        }
    }

}
