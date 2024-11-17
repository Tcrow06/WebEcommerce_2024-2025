package com.webecommerce.controller.chat;

import com.google.gson.Gson;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.service.ICategoryService;
import javax.servlet.annotation.WebServlet;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.enterprise.inject.spi.CDI;

@ServerEndpoint("/chat")
public class ChatEndpoint {

    private static Map<String, Session> userSessions = new ConcurrentHashMap<>();
    private static Map<String, String> questions;
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private ICategoryService categoryService;

    static {
        try (Reader reader = new FileReader(ChatEndpoint.class.getClassLoader().getResource("chat.json").getFile())) {
            Gson gson = new Gson();
            questions = gson.fromJson(reader, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        userSessions.put(session.getId(), session);
        System.out.println("Client connected: " + session.getId());
        categoryService = CDI.current().select(ICategoryService.class).get();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Received from " + session.getId() + ": " + message);

        String keyFormat = null;
        String responseKey = forecast(questions, message, "key");
        if (responseKey.startsWith("Có vẻ")) {
            sendPrivateMessage(session.getId(), responseKey);
            responseKey = formatKey(responseKey);
        }

        String responseValue = forecast(questions, message, "value");
        sendPrivateMessage(session.getId(), responseValue);

        if ("sản phẩm".equalsIgnoreCase(responseKey)) {
            new Thread(() -> {
                List<String> products = fetchProductsFromDB();
                try {
                    if (products.isEmpty()) {
                        sendPrivateMessage(session.getId(), "Hiện tại không có sản phẩm nào.");
                    } else {
                        sendPrivateMessage(session.getId(), "Danh sách sản phẩm: " + String.join(", ", products));
                        for (String product : products) {
                            System.out.println("Sản phẩm: " + product);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @OnClose
    public void onClose(Session session) {
        userSessions.remove(session.getId());
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    private void sendPrivateMessage(String sessionId, String message) throws IOException {
        Session session = userSessions.get(sessionId);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> fetchProductsFromDB() {
        List<String> products = new ArrayList<>();
        List<CategoryDTO> productsSearch = categoryService.findAll();
        for (CategoryDTO categoryDTO : productsSearch) {
            products.add(categoryDTO.getName());
        }
        return products;
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(
                                    dp[i - 1][j] + 1,
                                    dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + cost);
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    private String forecast(Map<String, String> map, String message, String target) {
        message = message.trim();
        String closestKey = null;
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;

        for (String key : map.keySet()) {
            int distance = levenshteinDistance(message, key);
            if (distance < minDistance) {
                minDistance = distance;
                closestKey = key;
                if (message.contains(closestKey)) found = true;
            }
        }

        if (found == true) {
            if (target == "key") return closestKey != null ? closestKey : null;
            else return closestKey != null ? map.get(closestKey) : null;
        } else {
            if (target == "key") return closestKey != null ? "Có vẻ bạn đang muốn hỏi về " + closestKey : null;
            else return closestKey != null ? map.get(closestKey) : null;
        }
    }

    private String formatKey(String key) {
        String prefix = "Có vẻ bạn đang muốn hỏi về ";
        if (key.startsWith(prefix)) {
            return key.substring(prefix.length());
        }
        return null;
    }
}