package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.IMessageDAO;
import com.webecommerce.entity.other.MessageEntity;

public class MessageDAODAO extends AbstractDAO<MessageEntity> implements IMessageDAO {
    public MessageDAODAO() {
        super(MessageEntity.class);
    }
}
