package com.webecommerce.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private final String ServerName = "localhost";
    private final String dbName = "webecommerce_final";
    private final String portNumber = "3306";
    private final String userID = "root";
    private final String password = "@Minhtoan2004";

    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Sửa đường dẫn driver
            String url = "jdbc:mysql://" + ServerName + ":" + portNumber + "/" + dbName;
            return DriverManager.getConnection(url, userID, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
            throw e; // Bắt lỗi để theo dõi
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = new DBContext().getConnection();
            if (conn != null) {
                System.out.println("Kết nối thành công!");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hiển thị lỗi để kiểm tra
        }
    }
}
