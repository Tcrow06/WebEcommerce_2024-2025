package com.webecommerce.paging;

import com.webecommerce.filter.FilterProduct;
import com.webecommerce.filter.FilterProductVariant;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    FilterProduct getFilterProduct();
    FilterProductVariant getFilterProductVariant();
}
