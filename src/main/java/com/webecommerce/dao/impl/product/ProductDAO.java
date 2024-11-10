package com.webecommerce.dao.impl.product;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.mapper.Impl.ProductMapper;
import com.webecommerce.paging.Pageable;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class ProductDAO extends AbstractDAO<ProductEntity> implements IProductDAO {

    @Inject
    private ProductMapper productMapper;
    public ProductDAO() {
        super(ProductEntity.class);
    }

    // Phương thức lấy sản phẩm theo category code
    public List<ProductEntity> findProductsByCategoryCode(String categoryCode) {
        String query = "SELECT p FROM ProductEntity p WHERE p.category.code = :code";
        try {
            return entityManager.createQuery(query, ProductEntity.class)
                    .setParameter("code", categoryCode)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy sản phẩm theo category code: " + categoryCode, e);
            return null;
        }
    }

    public List<String> getListColorBySize (String size, Long productId) {
        String query = "SELECT p.color FROM ProductVariantEntity p WHERE p.product.id = :id AND p.size = :size";
        try {
            return entityManager.createQuery(query, String.class)
                    .setParameter("id", productId)
                    .setParameter("size", size)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching product colors by size and product ID: " + productId, e);
            return null;
        }
    }

    public List<String> getListSizeByColor (String color, Long productId) {
        String query = "SELECT p.size FROM ProductVariantEntity p WHERE p.product.id = :id AND p.color = :color";
        try {
            return entityManager.createQuery(query, String.class)
                    .setParameter("id", productId)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching product sizes by size and product ID: " + productId, e);
            return null;
        }
    }

    public List <String> getBrands () {
        String query = "SELECT DISTINCT p.brand FROM ProductEntity p";
        try {
            return entityManager.createQuery(query, String.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "No brand found", e);
            return null;
        }
    }

    public List<ProductEntity> findProductsByBrand(String brand) {
        return super.findByAttribute("brand",brand);
    }

    @Override
    public List<ProductEntity> findProductOnSale(int limit) {
        String query = "SELECT p FROM ProductEntity p " +
                "JOIN p.productDiscount d " +
                "WHERE d.startDate <= :currentDate AND d.endDate >= :currentDate";
        try {
            LocalDateTime currentDate = LocalDateTime.now();
            return entityManager.createQuery(query, ProductEntity.class)
                    .setParameter("currentDate", currentDate)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy sản phẩm có discount còn hiệu lực", e);
            return null;
        }
    }

    @Override
    public List<ProductEntity> findProductIsNew(int limit) {
        String query = "SELECT p FROM ProductEntity p WHERE p.isNew BETWEEN :startDate AND :endDate";
        try {
            LocalDateTime endDate = LocalDateTime.now();
            LocalDateTime startDate = endDate.minusDays(7);
            return entityManager.createQuery(query, ProductEntity.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy sản phẩm mới", e);
            return null;
        }
    }

    @Override
    public List<ProductEntity> findProductOther(int limit) {
        String query = "SELECT p FROM ProductEntity p " +
                "LEFT JOIN p.productDiscount d " +
                "WHERE (d IS NULL OR d.endDate < :currentDate) " +
                "AND p.isNew <= :sevenDaysAgo";
        try {
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime sevenDaysAgo = currentDate.minusDays(7);
            return entityManager.createQuery(query, ProductEntity.class)
                    .setParameter("currentDate", currentDate)
                    .setParameter("sevenDaysAgo", sevenDaysAgo)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm sản phẩm không có discount hoặc discount đã hết hạn và bán sau 7 ngày", e);
            return null;
        }
    }

    public Long getTotalItem() {
        return (Long) entityManager.createQuery("SELECT COUNT(p) FROM ProductEntity p")
                .getSingleResult();
    }

    @Override
    public List<ProductEntity> findAll(Pageable pageable) {

        StringBuilder jpql = new StringBuilder("SELECT n FROM ProductEntity n WHERE 1=1");

        if (pageable.getFilterProduct().getFilterCategory() != -1) {
            jpql.append(" AND n.category.id = :categoryId");
        }

        if (pageable.getFilterProduct().getFilterBrand() != null && !pageable.getFilterProduct().getFilterBrand().isEmpty()) {
            jpql.append(" AND n.brand = :brand");
        }

        String queryStr = jpql.toString();

        TypedQuery<ProductEntity> query = entityManager.createQuery(queryStr, ProductEntity.class);

        if (pageable.getFilterProduct().getFilterCategory() != -1) {
            query.setParameter("categoryId", Long.valueOf(pageable.getFilterProduct().getFilterCategory()));
        }

        // Đặt giá trị tham số cho brand_id nếu có
        if (pageable.getFilterProduct().getFilterBrand() != null && !pageable.getFilterProduct().getFilterBrand().isEmpty()) {
            query.setParameter("brand", pageable.getFilterProduct().getFilterBrand());
        }

        if (pageable.getOffset() != null) {
            query.setFirstResult(pageable.getOffset());
        }
        if (pageable.getLimit() != null) {
            query.setMaxResults(pageable.getLimit());
        }

        List<ProductEntity> productEntities = query.getResultList();

        return productEntities;
    }

    @Override
    public List<String> getAllProductName() {
        String query = "SELECT p.name FROM ProductEntity p ";
        try {
            LocalDateTime currentDate = LocalDateTime.now();
            return entityManager.createQuery(query, String.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy sản phẩm có discount còn hiệu lực", e);
            return null;
        }
    }
}
