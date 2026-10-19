package com.shopsphere.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DB {
    private DB() {}

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MariaDB JDBC driver not found", e);
        }

        String url = "jdbc:mariadb://localhost:3306/shopsphere";

        return DriverManager.getConnection(url, "root", "");
    }
}