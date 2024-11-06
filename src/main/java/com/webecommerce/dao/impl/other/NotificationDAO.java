package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.INotificationDAO;
import com.webecommerce.entity.other.NotificationEntity;

public class NotificationDAO extends AbstractDAO <NotificationEntity> implements INotificationDAO {
    public NotificationDAO() {
        super(NotificationEntity.class);
    }
}
