package com.webecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dto.discount.ProductDiscountDTO;

import javax.servlet.http.Part;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO extends BaseDTO<ProductDTO> {

    private String name;

    public ProductDTO(String name, boolean highlight, String brand, String description, CategoryDTO category, Part sizeConversionTable) {
        this.name = name;
        this.highlight = highlight;
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.sizeConversionTable = sizeConversionTable;
    }

    public ProductDTO () {}

    private boolean highlight;

    private EnumProductStatus status;

    private boolean isNew;


    private LocalDateTime isNewProduct;

    private String brand;

    private String description;

    private String sizeConversionTableUrl;

    private CategoryDTO category;

    private List<ProductVariantDTO> productVariants = new ArrayList<>();

    private ProductDiscountDTO productDiscount;


    private String photo;

    public LocalDateTime getIsNewProduct() {
        return isNewProduct;
    }

    public void setIsNewProduct(LocalDateTime isNewProduct) {
        this.isNewProduct = isNewProduct;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        if (price != 0) return price;
        if (productVariants != null) {
            for (ProductVariantDTO productVariant : productVariants) {
                if (price == 0) price = productVariant.getPrice();
                else if (price > productVariant.getPrice())
                    price = productVariant.getPrice(); // Lấy giá thâp nhâất
            }
            return price;
        }
        return 0;
    }

    public double getOriginalPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;


    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
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
    public void setStatus(String status) {
        this.status = EnumProductStatus.valueOf(status);
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }

    public boolean getIsNew() {
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

    public List<ProductVariantDTO> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants (List<ProductVariantDTO> productVariants) {
        this.productVariants = productVariants;
    }

    private Part sizeConversionTable;


    public List<String> getColorList() {
        List<String> colorList = new ArrayList<>();
        for (ProductVariantDTO productVariantDTO : productVariants) {
            if (colorList.contains(productVariantDTO.getColor())) continue;
            colorList.add(productVariantDTO.getColor());
        }
        return colorList;
    }

    public List<String> getSizeList () {
        List<String> sizeList = new ArrayList<>();
        for (ProductVariantDTO productVariantDTO : productVariants) {
            if (sizeList.contains(productVariantDTO.getSize())) continue;
            sizeList.add(productVariantDTO.getSize());
        }
        return sizeList;
    }

    public ProductDiscountDTO getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscountDTO productDiscount) {
        this.productDiscount = productDiscount;
    }
    
    public double getDiscountedPrice() {
        if (this.productDiscount == null) return price;
        if (price == 0) {
            for (ProductVariantDTO productVariantDTO : this.productVariants) {
                if (price == 0 || productVariantDTO.getPrice() < productVariantDTO.getPrice())
                    price = productVariantDTO.getPrice();
            }
        }
        return new BigDecimal(
                price - (price / 100) * productDiscount.getDiscountPercentage()
        ).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public String getSizeConversionTableUrl() {
        return sizeConversionTableUrl;
    }

    public void setSizeConversionTableUrl(String sizeConversionTableUrl) {
        this.sizeConversionTableUrl = sizeConversionTableUrl;
    }

    public Part getSizeConversionTable() {
        return sizeConversionTable;
    }

    public void setSizeConversionTable(Part sizeConversionTable) {
        this.sizeConversionTable = sizeConversionTable;
    }
}
