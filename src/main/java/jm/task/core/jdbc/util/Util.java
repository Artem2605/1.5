package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbforkata";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
