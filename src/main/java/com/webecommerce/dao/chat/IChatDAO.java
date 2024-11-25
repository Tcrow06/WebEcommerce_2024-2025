package com.webecommerce.dao.chat;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.chat.ChatEntity;

import java.util.List;

public interface IChatDAO extends GenericDAO<ChatEntity> {
    List<ChatEntity> getAllChats();
    String getCustomerNameByUserId(Long userId);
    String getOwnerNameByUserId(Long userId);
}
