package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IReturnOrderDAO;
import com.webecommerce.entity.order.ReturnOrderEntity;

public class ReturnOrderDAO extends AbstractDAO<ReturnOrderEntity> implements IReturnOrderDAO {
    public ReturnOrderDAO() {
        super(ReturnOrderEntity.class);
    }
}
