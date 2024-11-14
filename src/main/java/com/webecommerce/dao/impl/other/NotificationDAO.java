package com.webecommerce.dao.impl.other;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.INotificationDAO;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.entity.other.NotificationEntity;

import java.util.List;
import java.util.logging.Level;

public class NotificationDAO extends AbstractDAO <NotificationEntity> implements INotificationDAO {
    public NotificationDAO() {
        super(NotificationEntity.class);
    }

}
