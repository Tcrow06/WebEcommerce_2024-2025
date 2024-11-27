package com.webecommerce.service.impl;

import com.webecommerce.dao.chat.IChatDAO;
import com.webecommerce.entity.chat.ChatEntity;
import com.webecommerce.service.IChatService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

public class ChatService implements IChatService {

    @Inject
    private IChatDAO chatDAO;

    @Override
    public List<ChatEntity> getAllChats() {
        return chatDAO.getAllChats();
    }

    @Override
    public String getCustomerNameByUserId(Long userId) {
        return chatDAO.getCustomerNameByUserId(userId);
    }

    @Override
    public String getOwnerNameByUserId(Long userId) {
        return chatDAO.getOwnerNameByUserId(userId);
    }

    @Override
    public void saveChat(LocalDateTime dt, String message, Long userId) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setCreatedAt(dt);
        chatEntity.setMessage(message);
        chatEntity.setUserId(userId);
        chatDAO.insert(chatEntity);
    }
}
