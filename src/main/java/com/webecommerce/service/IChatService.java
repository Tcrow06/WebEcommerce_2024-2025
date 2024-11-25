package com.webecommerce.service;

import com.webecommerce.entity.chat.ChatEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IChatService {
    List<ChatEntity> getAllChats();
    String getCustomerNameByUserId(Long userId);
    void saveChat(LocalDateTime dt, String message, Long userId);
    String getOwnerNameByUserId(Long userId);
}
