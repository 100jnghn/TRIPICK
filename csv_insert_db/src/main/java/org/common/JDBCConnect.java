package org.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCConnect {
    private static Connection conn = null;

    public static void connect() {
        if (conn != null) return;
        try (InputStream is = JDBCConnect.class.getResourceAsStream("/driver.properties")) {
            Properties prop = new Properties();
            prop.load(is);

            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            System.out.println("DB 연결 성공");
        } catch (SQLException | IOException e) {
            throw new RuntimeException("DB 연결 실패", e);
        }
    }

    /**
     * 이미 연결된 Connection 반환
     */
    public static Connection getConnection() {
        if (conn == null) {
            throw new IllegalStateException("Connection is null. connect()를 먼저 호출하세요.");
        }
        return conn;
    }

    public static void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("닫기 -- Connection");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {

        try {
            if (stmt != null && !stmt.isClosed()) {
                System.out.println("닫기 -- Statement");
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void commit() {
        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println(" commit -------------");
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println(" rollback -------------");
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
