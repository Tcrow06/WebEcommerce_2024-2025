package com.webecommerce.filter;

public class FilterProduct {
    private int filterCategory;

    private String filterBrand;

    public FilterProduct(int filterCategory, String filterBrand) {
        this.filterCategory = filterCategory;
        this.filterBrand = filterBrand;
    }

    public String getFilterBrand() {
        return filterBrand;
    }

    public void setFilterBrand(String filterBrand) {
        this.filterBrand = filterBrand;
    }

    public int getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(int filterCategory) {
        this.filterCategory = filterCategory;
    }
}
