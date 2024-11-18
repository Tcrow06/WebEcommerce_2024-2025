package com.webecommerce.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class CacheFactory {
    private static JedisPool jedisPool;

    static {
        try {
            // Sử dụng ClassLoader để đọc file cấu hình từ thư mục resources
            ClassLoader classLoader = CacheFactory.class.getClassLoader();
            try (InputStream inputStream = classLoader.getResourceAsStream("redis-config.xml")) {
                if (inputStream == null) {
                    throw new RuntimeException("Không tìm thấy file cấu hình redis-config.xml trong resources");
                }

                // Phân tích file cấu hình XML
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputStream);

                // Truy xuất các tham số kết nối từ XML
                doc.getDocumentElement().normalize();
                Element redisConfig = (Element) doc.getElementsByTagName("redis").item(0);

                String host = redisConfig.getElementsByTagName("host").item(0).getTextContent();
                int port = Integer.parseInt(redisConfig.getElementsByTagName("port").item(0).getTextContent());
                int maxTotal = Integer.parseInt(redisConfig.getElementsByTagName("maxTotal").item(0).getTextContent());
                int maxIdle = Integer.parseInt(redisConfig.getElementsByTagName("maxIdle").item(0).getTextContent());
                int minIdle = Integer.parseInt(redisConfig.getElementsByTagName("minIdle").item(0).getTextContent());

                // Cấu hình JedisPoolConfig
                JedisPoolConfig poolConfig = new JedisPoolConfig();
                poolConfig.setMaxTotal(maxTotal);
                poolConfig.setMaxIdle(maxIdle);
                poolConfig.setMinIdle(minIdle);

                // Khởi tạo JedisPool
                jedisPool = new JedisPool(poolConfig, host, port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để lấy một kết nối Jedis
    public static Jedis getConnection() {
        return jedisPool.getResource();
    }

    // Phương thức để đóng kết nối JedisPool khi không sử dụng nữa
    public static void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }
}
