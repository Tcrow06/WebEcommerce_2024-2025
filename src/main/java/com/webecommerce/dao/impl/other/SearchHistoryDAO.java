package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.ISearchHistoryDAO;
import com.webecommerce.entity.other.SearchHistoryEntity;

public class SearchHistoryDAO extends AbstractDAO<SearchHistoryEntity> implements ISearchHistoryDAO {
    public SearchHistoryDAO() {
        super(SearchHistoryEntity.class);
    }
}
