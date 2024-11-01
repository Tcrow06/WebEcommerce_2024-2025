package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.INotificationDAO;
import com.webecommerce.entity.other.NotificationEntity;

public class NotificationDAODAO extends AbstractDAO <NotificationEntity> implements INotificationDAO {
    public NotificationDAODAO() {
        super(NotificationEntity.class);
    }
}
