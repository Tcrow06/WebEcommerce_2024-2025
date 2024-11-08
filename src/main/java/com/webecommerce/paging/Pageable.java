package com.webecommerce.paging;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
}
