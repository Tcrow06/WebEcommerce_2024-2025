package com.webecommerce.paging;

import com.webecommerce.filter.FilterProduct;
import com.webecommerce.filter.FilterProductVariant;
import com.webecommerce.sort.Sorter;

public class PageRequest implements Pageable{

    private Integer page;

    private Integer maxPageItem;

    private FilterProduct filterProduct;

    private FilterProductVariant filterProductVariant;

    private Sorter sorter;

    public PageRequest(Integer page, Integer maxPageItem) {
        this.page = page;
        this.maxPageItem = maxPageItem;
    }

    public PageRequest(Integer page, Integer maxPageItem, FilterProduct filterProduct, FilterProductVariant filterProductVariant, Sorter sorter) {
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.filterProduct = filterProduct;
        this.filterProductVariant = filterProductVariant;
        this.sorter = sorter;
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

    @Override
    public FilterProductVariant getFilterProductVariant() {
        if(this.filterProductVariant != null) {
            return this.filterProductVariant;
        }
        return null;
    }

    @Override
    public Sorter getSorter() {
        if(this.sorter != null) {
            return this.sorter;
        }
        return null;
    }

}
