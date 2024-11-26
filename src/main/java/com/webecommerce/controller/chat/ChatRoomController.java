package com.webecommerce.controller.chat;

import com.webecommerce.entity.chat.ChatEntity;
import com.webecommerce.service.IChatService;
import com.webecommerce.utils.JWTUtil;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat-room")
public class ChatRoomController {
    private static Map<Long, Session> chatRoomSessions = new ConcurrentHashMap<>();

    private IChatService chatService = CDI.current().select(IChatService.class).get();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        String token = getToken(session);

        List<ChatEntity> messages = chatService.getAllChats();
        if (messages != null) {
            for (ChatEntity chatEntity : messages) {
                String name = chatService.getCustomerNameByUserId(chatEntity.getUserId());
                if (name == null) {
                    name = "Chủ cửa hàng";
                }
                sendPrivateMessage(session, name, chatEntity.getMessage());
            }
        }

        if (token != null) {
            String role = JWTUtil.getRoleFromToken(token);
            Long userId = JWTUtil.getIdUserFromToken(token);

            if (userId != null) {
                chatRoomSessions.put(userId, session);
                Session ss = chatRoomSessions.get(userId);
                if (ss != null) {

                    String name = chatService.getCustomerNameByUserId(userId);
                    if ("OWNER".equals(role)) {
                        broadcastMessage("Hệ thống", "Chủ cửa hàng" + " đã tham gia!");
                    } else {
                        broadcastMessage("Hệ thống", name + " đã tham gia!");
                    }
//                    broadcastMessage("Hệ thống", name + " đã tham gia!");
                }
            }
        } else {
            sendPrivateMessage(session, "Hệ thống", "Bạn cần đăng nhập để tham gia thảo luận!");
        }
    }


    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String token = getToken(session);
        if (token != null) {
            String role = JWTUtil.getRoleFromToken(token);
            Long userId = JWTUtil.getIdUserFromToken(token);
            if (userId != null) {
                String name = chatService.getCustomerNameByUserId(userId);
                if ("OWNER".equals(role)) {
                    broadcastMessage("Chủ cửa hàng", message); // Hiển thị tin nhắn từ admin
                } else {
                    broadcastMessage(name, message); // Hiển thị tin nhắn từ user
                }
//                broadcastMessage(name, message);
                LocalDateTime dt = LocalDateTime.now();
                chatService.saveChat(dt, message, userId);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        String token = getToken(session);
        if (token != null) {
            Long userId = JWTUtil.getIdUserFromToken(token);
            if (userId != null) {
                chatRoomSessions.remove(userId);
                String name = chatService.getCustomerNameByUserId(userId);
                if (name == null) name = "Chủ cửa hàng";
                broadcastMessage("Hệ thống", name + " đã rời đi!");
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    private void broadcastMessage(String sender, String message) {
        chatRoomSessions.values().forEach(session -> {
            try {
                session.getBasicRemote().sendText(sender + ": " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void sendPrivateMessage(Session session, String name, String message) throws IOException {
        if (session != null) {
            try {
                session.getBasicRemote().sendText(name + ": " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getToken(Session session) {
        String token = null;
        Map<String, List<String>> requestParams = session.getRequestParameterMap();
        if (requestParams.containsKey("token") && requestParams.get("token") != null && !requestParams.get("token").isEmpty()) {
            token = requestParams.get("token").get(0); // Lấy giá trị đầu tiên của danh sách
        }
        return token;
    }
}
