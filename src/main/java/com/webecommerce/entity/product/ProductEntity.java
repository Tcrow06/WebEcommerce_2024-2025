package com.webecommerce.entity.product;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private boolean highlight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumProductStatus status;

    public LocalDateTime getIsNew() {
        return isNew;
    }

    public void setIsNew(LocalDateTime isNew) {
        this.isNew = isNew;
    }

    @Column(name = "is_new")
    private LocalDateTime isNew;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "size_conversion_table_url")
    private String sizeConversionTableUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantEntity> productVariants = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_discount_id", referencedColumnName = "id")
    private ProductDiscountEntity productDiscount ;

    public ProductDiscountEntity getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscountEntity productDiscount) {
        this.productDiscount = productDiscount;
    }



    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ProductVariantEntity> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariantEntity> productVariants) {
        this.productVariants = productVariants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public EnumProductStatus getStatus() {
        return status;
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSizeConversionTableUrl() {
        return sizeConversionTableUrl;
    }

    public void setSizeConversionTableUrl(String sizeConversionTableUrl) {
        this.sizeConversionTableUrl = sizeConversionTableUrl;
    }
}
