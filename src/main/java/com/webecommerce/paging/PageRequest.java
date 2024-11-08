package com.webecommerce.paging;

import com.webecommerce.filter.FilterProduct;

public class PageRequest implements Pageable{

    private Integer page;

    private Integer maxPageItem;

    private FilterProduct filterProduct;

    public PageRequest(Integer page, Integer maxPageItem) {
        this.page = page;
        this.maxPageItem = maxPageItem;
    }

    public PageRequest(Integer page, Integer maxPageItem, FilterProduct filterProduct) {
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.filterProduct = filterProduct;
    }



    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if(this.page != null && this.maxPageItem != null) {
            return (this.page - 1) * this.maxPageItem;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItem;
    }

    @Override
    public FilterProduct getFilterProduct() {
        if(this.filterProduct != null) {
            return this.filterProduct;
        }
        return null;
    }
}
