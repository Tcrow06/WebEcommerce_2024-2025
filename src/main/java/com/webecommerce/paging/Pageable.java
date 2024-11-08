package com.webecommerce.paging;

import com.webecommerce.filter.FilterProduct;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    FilterProduct getFilterProduct();
}
