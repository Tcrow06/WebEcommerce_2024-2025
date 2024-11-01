package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.ISearchHistoryDAO;
import com.webecommerce.entity.other.SearchHistoryEntity;

public class SearchHistoryDAODAO extends AbstractDAO<SearchHistoryEntity> implements ISearchHistoryDAO {
    public SearchHistoryDAODAO() {
        super(SearchHistoryEntity.class);
    }
}
