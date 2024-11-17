package com.webecommerce.service.impl;

import com.webecommerce.dao.other.INotificationDAO;
import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.INotificationService;
import javax.inject.Inject;
import java.util.List;

public class NotificationService implements INotificationService {
    @Inject
    INotificationDAO notificationDAO;
}
