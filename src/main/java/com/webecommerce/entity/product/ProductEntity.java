package com.webecommerce.entity.product;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import javax.persistence.*;
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

    @Column(name = "is_new")
    private boolean isNew;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantEntity> productVariants = new ArrayList<>();

    @OneToOne(mappedBy = "product")
    private ProductDiscountEntity productDiscounts ;

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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
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

    public ProductDiscountEntity getProductDiscounts() {
        return productDiscounts;
    }

    public void setProductDiscounts(ProductDiscountEntity productDiscounts) {
        this.productDiscounts = productDiscounts;
    }
}
