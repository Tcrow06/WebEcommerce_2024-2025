package com.webecommerce.dao.impl.product;

import antlr.StringUtils;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.mapper.Impl.ProductMapper;
import com.webecommerce.paging.Pageable;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

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
        // Truy vấn duy nhất để lấy danh sách productIds dựa trên các điều kiện lọc và giá tối thiểu
        StringBuilder jpql = new StringBuilder(
                "SELECT DISTINCT p FROM ProductEntity p " +
                        "JOIN p.productVariants v " +
                        "LEFT JOIN p.productDiscount d " +
                        "WHERE 1=1"
        );

        // Điều kiện lọc
        if (pageable.getFilterProduct().getFilterCategory() != -1) {
            jpql.append(" AND p.category.id = :categoryId");
        }

        if (pageable.getFilterProduct().getFilterBrand() != null &&
                !pageable.getFilterProduct().getFilterBrand().isEmpty()) {
            jpql.append(" AND p.brand = :brand");
        }

        jpql.append(
                " AND v.price = (SELECT MIN(v2.price) FROM ProductVariantEntity v2 WHERE v2.product.id = p.id)"
        );

        String tag = pageable.getFilterProduct().getTag();
        double minPrice = pageable.getFilterProductVariant().getMinPrice();
        double maxPrice = pageable.getFilterProductVariant().getMaxPrice();
        if (!Double.isNaN(minPrice) && !Double.isNaN(maxPrice)) {
            jpql.append(" AND v.price BETWEEN :minPrice AND :maxPrice");
        } else if (!Double.isNaN(minPrice)) {
            jpql.append(" AND v.price >= :minPrice");
        } else if (!Double.isNaN(maxPrice)) {
            jpql.append(" AND v.price <= :maxPrice");
        }

        if (tag != null) {
            if (tag.equals("new")) {
                jpql.append(" AND DATEDIFF(CURRENT_DATE, p.isNew) <= 7");
            } else if (tag.equals("sale")) {
                jpql.append(" AND d.startDate <= CURRENT_DATE AND d.endDate >= CURRENT_DATE");
            } else if (tag.equals("other")) {
                jpql.append(" AND (DATEDIFF(CURRENT_DATE, p.isNew) > 7 OR p.isNew IS NULL)");
                jpql.append(" AND (d.startDate > CURRENT_DATE OR d.endDate < CURRENT_DATE OR d.startDate IS NULL OR d.endDate IS NULL)");
            }
        }

        TypedQuery<ProductEntity> query = entityManager.createQuery(jpql.toString(), ProductEntity.class);

        if (pageable.getFilterProduct().getFilterCategory() != -1) {
            query.setParameter("categoryId", Long.valueOf(pageable.getFilterProduct().getFilterCategory()));
        }

        if (pageable.getFilterProduct().getFilterBrand() != null &&
                !pageable.getFilterProduct().getFilterBrand().isEmpty()) {
            query.setParameter("brand", pageable.getFilterProduct().getFilterBrand());
        }

        if (!Double.isNaN(minPrice)) {
            query.setParameter("minPrice", minPrice);
        }
        if (!Double.isNaN(maxPrice)) {
            query.setParameter("maxPrice", maxPrice);
        }

        // Thực thi truy vấn để lấy danh sách sản phẩm
        List<ProductEntity> productEntities = query.getResultList();

        // Thực hiện sắp xếp danh sách sản phẩm sau khi truy vấn xong
        if (pageable.getSorter().getSortBy() != null) {
            String sortBy = pageable.getSorter().getSortBy();
            if ("asc".equalsIgnoreCase(sortBy)) {
                productEntities.sort(Comparator.comparing(p ->
                        p.getProductVariants().stream().mapToDouble(v -> v.getPrice()).min().orElse(Double.MAX_VALUE)));
            } else if ("desc".equalsIgnoreCase(sortBy)) {
                productEntities.sort(Comparator.comparing((ProductEntity p) ->
                        p.getProductVariants().stream().mapToDouble(v -> v.getPrice()).min().orElse(Double.MAX_VALUE)).reversed());
            }
        }

        // Thực hiện phân trang cho danh sách sản phẩm đã sắp xếp
        int offset = pageable.getOffset() != null ? pageable.getOffset() : 0;
        int limit = pageable.getLimit() != null ? pageable.getLimit() : 9;
        return productEntities.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

}
