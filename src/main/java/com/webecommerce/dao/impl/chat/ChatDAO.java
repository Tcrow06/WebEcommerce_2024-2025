package com.webecommerce.dao.impl.chat;

import com.webecommerce.dao.chat.IChatDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.chat.ChatEntity;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatDAO extends AbstractDAO<ChatEntity> implements IChatDAO {

    private static final Logger LOGGER = Logger.getLogger(ChatDAO.class.getName());

    public ChatDAO() {
        super(ChatEntity.class);
    }

    @Override
    public List<ChatEntity> getAllChats() {
        String query = "SELECT c FROM ChatEntity c ORDER BY c.createdAt ASC";
        try {
            TypedQuery<ChatEntity> typedQuery = entityManager.createQuery(query, ChatEntity.class);
            return typedQuery.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy tất cả lịch sử chat", e);
            return null;
        }
    }

    @Override
    public String getCustomerNameByUserId(Long userId) {
        String query = "SELECT c.name FROM CustomerEntity c WHERE c.id = :userId";
        try {
            TypedQuery<String> typedQuery = entityManager.createQuery(query, String.class);
            typedQuery.setParameter("userId", userId);
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getOwnerNameByUserId(Long userId) {
        String query = "SELECT o.name FROM OwnerEntity o WHERE o.id = :userId";
        try {
            TypedQuery<String> typedQuery = entityManager.createQuery(query, String.class);
            typedQuery.setParameter("userId", userId);
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
