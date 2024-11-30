package com.webecommerce.dao.impl.product;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dto.notinentity.RevenueDTO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.mapper.Impl.ProductMapper;
import com.webecommerce.paging.Pageable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ProductDAO extends AbstractDAO<ProductEntity> implements IProductDAO {

    @Inject
    private ProductMapper productMapper;
    private Long totalItem;
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
        String query = "SELECT p.color FROM ProductVariantEntity p WHERE p.product.id = :id AND p.size = :size AND p.status = : status";
        try {
            return entityManager.createQuery(query, String.class)
                    .setParameter("id", productId)
                    .setParameter("size", size)
                    .setParameter("status", EnumProductStatus.SELLING)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching product colors by size and product ID: " + productId, e);
            return null;
        }
    }

    public List<String> getListSizeByColor (String color, Long productId) {
        String query = "SELECT p.size FROM ProductVariantEntity p WHERE p.product.id = :id AND p.color = :color AND p.status = : status";
        try {
            return entityManager.createQuery(query, String.class)
                    .setParameter("id", productId)
                    .setParameter("color", color)
                    .setParameter("status", EnumProductStatus.SELLING)
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
    @Override
    public List<ProductEntity> findProductSuggestion(Long categoryId,int limit, Long productId) {
        String query = "SELECT p FROM ProductEntity p " +
                "WHERE p.category.id = : categoryId and p.id <> :productId and p.status = :status";
        try {
            return entityManager.createQuery(query, ProductEntity.class)
                    .setParameter("categoryId", categoryId)
                    .setParameter("productId", productId)
                    .setParameter("status", EnumProductStatus.SELLING)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm sản phẩm không có discount hoặc discount đã hết hạn và bán sau 7 ngày", e);
            return null;
        }
    }


    public List<ProductEntity> findProductByStatus(EnumProductStatus status) {
        return super.findByAttributeCustom("status", status);
    }

    public List<ProductEntity> findProductByCategoryOrStatusOrName(String categoryCode, EnumProductStatus status, String name) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM ProductEntity p WHERE 1=1"); // Sử dụng 1=1 để giữ WHERE hợp lệ

        if (categoryCode != null) {
            jpql.append(" AND p.category.code = :category");
        }
        if (status != null) {
            jpql.append(" AND p.status = :status");
        }
        if (name != null) {
            jpql.append(" AND LOWER(p.name) LIKE :name");
        }

        EntityManager em = super.getEntityManager();

        try {

            TypedQuery<ProductEntity> query = em.createQuery(jpql.toString(), ProductEntity.class);

            if (categoryCode != null) {
                query.setParameter("category", categoryCode);
            }
            if (status != null) {
                query.setParameter("status", status);
            }
            if (name != null) {
                query.setParameter("name", "%" + name.toLowerCase() + "%");
            }

            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            super.closeEntityManager(em);
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
                        "WHERE p.status = 'SELLING'"
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

        EntityManager em = super.getEntityManager();
        try {

            TypedQuery<ProductEntity> query = em.createQuery(jpql.toString(), ProductEntity.class);

            String countJpql = jpql.toString().replace("SELECT DISTINCT p", "SELECT COUNT(DISTINCT p)");
            TypedQuery<Long> countQuery = em.createQuery(countJpql, Long.class);

            if (pageable.getFilterProduct().getFilterCategory() != -1) {
                query.setParameter("categoryId", Long.valueOf(pageable.getFilterProduct().getFilterCategory()));

                countQuery.setParameter("categoryId", Long.valueOf(pageable.getFilterProduct().getFilterCategory()));

            }

            if (pageable.getFilterProduct().getFilterBrand() != null &&
                    !pageable.getFilterProduct().getFilterBrand().isEmpty()) {
                query.setParameter("brand", pageable.getFilterProduct().getFilterBrand());

                countQuery.setParameter("brand", pageable.getFilterProduct().getFilterBrand());
            }

            if (!Double.isNaN(minPrice)) {
                query.setParameter("minPrice", minPrice);

                countQuery.setParameter("minPrice", minPrice);

            }
            if (!Double.isNaN(maxPrice)) {
                query.setParameter("maxPrice", maxPrice);

                countQuery.setParameter("maxPrice", maxPrice);

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


            totalItem = countQuery.getSingleResult();
            // Thực hiện phân trang cho danh sách sản phẩm đã sắp xếp
            int offset = pageable.getOffset() != null ? pageable.getOffset() : 0;
            int limit = pageable.getLimit() != null ? pageable.getLimit() : 9;
            return productEntities.stream().skip(offset).limit(limit).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            super.closeEntityManager(em);
        }
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

    @Override
    public List<Object[]> findBestSellerProduct(int limit) {
        String jpql = "SELECT p, SUM(od.quantity) AS totalSales " +
                "FROM ProductEntity p " +
                "JOIN p.productVariants pv " +
                "JOIN OrderDetailEntity od ON pv.id = od.productVariant.id " +
                "JOIN od.order o " +
                "JOIN o.orderStatuses os " +
                "WHERE os.status = :status " +
                "GROUP BY p.id " +
                "ORDER BY totalSales DESC";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("status", EnumOrderStatus.PENDING);
        query.setMaxResults(limit); // Giới hạn kết quả trả về
        return query.getResultList();
    }

    @Override
    public List<Object[]> findLowestSellingProducts(int limit) {
        String jpql = "SELECT p, COALESCE(SUM(od.quantity), 0) AS totalSales " +
                "FROM ProductEntity p " +
                "LEFT JOIN p.productVariants pv " +
                "LEFT JOIN OrderDetailEntity od ON pv.id = od.productVariant.id " +
                "LEFT JOIN od.order o " +
                "LEFT JOIN o.orderStatuses os " +
                "WHERE os.status = :status OR os.status IS NULL " +
                "GROUP BY p.id " +
                "ORDER BY 2 ASC";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("status", EnumOrderStatus.PENDING);
        query.setMaxResults(limit); // Giới hạn kết quả trả về
        return query.getResultList();
    }


    @Override
    public int totalProducts() {
        String query = "SELECT COUNT(p) FROM ProductEntity p"; // Đếm tổng số sản phẩm
        try {
            Long count = entityManager.createQuery(query, Long.class)
                    .getSingleResult();
            return count != null ? count.intValue() : 0; // Chuyển đổi Long thành int
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tính tổng số sản phẩm", e);
            return 0; // Trả về 0 nếu xảy ra lỗi
        }
    }
    public Long getTotalItems() {
        return totalItem;
    }

    @Override
    public List<ProductEntity> searchProductsByName(String name) {
        String query = "SELECT p FROM ProductEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))";
        TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(query, ProductEntity.class);
        typedQuery.setParameter("name", name);
        typedQuery.setMaxResults(3);
        return typedQuery.getResultList();
    }

    @Override
    public List<ProductEntity> findAllByName(Pageable pageable, String name) {
        StringBuilder jpql = new StringBuilder(
                "SELECT DISTINCT p FROM ProductEntity p " +
                        "JOIN p.productVariants v " +
                        "LEFT JOIN p.productDiscount d " +
                        "WHERE p.status = 'SELLING'"
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

        jpql.append(" AND LOWER(p.name) LIKE :name");

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

        String countJpql = jpql.toString().replace("SELECT DISTINCT p", "SELECT COUNT(DISTINCT p)");
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);

        // Gán tham số truy vấn
        if (pageable.getFilterProduct().getFilterCategory() != -1) {
            query.setParameter("categoryId", Long.valueOf(pageable.getFilterProduct().getFilterCategory()));
            countQuery.setParameter("categoryId", Long.valueOf(pageable.getFilterProduct().getFilterCategory()));
        }

        if (pageable.getFilterProduct().getFilterBrand() != null &&
                !pageable.getFilterProduct().getFilterBrand().isEmpty()) {
            query.setParameter("brand", pageable.getFilterProduct().getFilterBrand());
            countQuery.setParameter("brand", pageable.getFilterProduct().getFilterBrand());
        }

        if (!Double.isNaN(minPrice)) {
            query.setParameter("minPrice", minPrice);
            countQuery.setParameter("minPrice", minPrice);
        }
        if (!Double.isNaN(maxPrice)) {
            query.setParameter("maxPrice", maxPrice);
            countQuery.setParameter("maxPrice", maxPrice);
        }

        query.setParameter("name", "%" + name.toLowerCase() + "%");
        countQuery.setParameter("name", "%" + name.toLowerCase() + "%");


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

        totalItem = countQuery.getSingleResult();
        // Thực hiện phân trang cho danh sách sản phẩm đã sắp xếp
        int offset = pageable.getOffset() != null ? pageable.getOffset() : 0;
        int limit = pageable.getLimit() != null ? pageable.getLimit() : 9;
        return productEntities.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }


    @Override
    public int countByStatus(EnumProductStatus status) {
        String query = "SELECT COUNT(p) FROM ProductEntity p WHERE p.status = :status"; // Câu JPQL đúng
        try {
            // Truy vấn COUNT trả về Long
            TypedQuery<Long> typedQuery = entityManager.createQuery(query, Long.class);
            typedQuery.setParameter("status", status);
            Long count = typedQuery.getSingleResult(); // Lấy kết quả
            return count != null ? count.intValue() : 0; // Chuyển Long thành int
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tính tổng số sản phẩm", e);
            return 0; // Trả về 0 nếu xảy ra lỗi
        }
    }
    @Override
    public RevenueDTO getRevenue() {
        String query = "SELECT \n" +
                "    pv.imageUrl AS imageUrl, \n" +
                "    p.name AS productName, \n" +
                "    p.brand AS brandName, \n" +
                "    SUM(od.quantity) AS purchaseQuantity, \n" +
                "    SUM(\n" +
                "        CASE \n" +
                "            WHEN pd.discountPercentage IS NOT NULL THEN \n" +
                "                od.quantity * pv.price * (1 - pd.discountPercentage / 100)\n" +
                "            ELSE \n" +
                "                od.quantity * pv.price \n" +
                "        END\n" +
                "    ) AS revenueTotal\n" +
                "FROM \n" +
                "    com.webecommerce.entity.order.OrderDetailEntity od\n" +
                "JOIN \n" +
                "    od.productVariant pv\n" +
                "JOIN \n" +
                "    pv.product p\n" +
                "JOIN \n" +
                "    od.order o\n" +
                "JOIN \n" +
                "    o.orderStatuses os\n" +
                "LEFT JOIN \n" +
                "    com.webecommerce.entity.order.ReturnOrderEntity ro ON od.id = ro.orderDetail.id\n" +
                "LEFT JOIN \n" +
                "    com.webecommerce.entity.discount.ProductDiscountEntity pd ON od.productDiscount.id = pd.id\n" +
                "WHERE \n" +
                "    os.status = 'RECEIVED' \n" +
                "    AND (ro.status IS NULL OR ro.status != 1)\n" +
                "GROUP BY \n" +
                "    pv.imageUrl, p.name, p.brand\n";





        List<Object[]> rawResults = entityManager.createQuery(query, Object[].class)
                .getResultList();

        return null;
    }
}
