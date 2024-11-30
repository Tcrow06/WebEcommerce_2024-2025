package com.webecommerce.filter;

public class FilterProduct {
    private int filterCategory;

    private String filterBrand;
    private String tag;

    public FilterProduct(int filterCategory, String filterBrand, String tag) {
        this.filterCategory = filterCategory;
        this.filterBrand = filterBrand;
        this.tag = tag;
    }
    public FilterProduct(int filterCategory) {
        this.filterCategory = filterCategory;
    }
    public FilterProduct() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
