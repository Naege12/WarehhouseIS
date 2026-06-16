package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/warehouse_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "190715";

    private static Connection connection;

    private ConnectDB() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Подключение к БД установлено");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ Ошибка подключения: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("🔌 Подключение к БД закрыто");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnect() {
        try {
            Connection con = getConnection();
            return con != null && !con.isClosed() && con.isValid(2);
        } catch (SQLException e) {
            System.err.println("❌ БД недоступна: " + e.getMessage());
            return false;
        }
    }
}