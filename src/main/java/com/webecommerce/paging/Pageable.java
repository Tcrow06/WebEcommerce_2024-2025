package com.webecommerce.paging;

import com.webecommerce.filter.FilterProduct;
import com.webecommerce.filter.FilterProductVariant;
import com.webecommerce.sort.Sorter;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    FilterProduct getFilterProduct();
    FilterProductVariant getFilterProductVariant();
    Sorter getSorter();
}
