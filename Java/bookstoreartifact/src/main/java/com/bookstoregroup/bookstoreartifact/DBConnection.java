package com.bookstoregroup.bookstoreartifact;

import com.zaxxer.hikari.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    private static DBConnection instance; // Singleton instance

    private DBConnection() { // Private constructor
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bookstore_db");
        config.setUsername("root");
        config.setPassword("root");

        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        config.setMinimumIdle(2);

        ds = new HikariDataSource(config);
    }

    // Public method to provide access to the singleton instance
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    // This is now a non-static method
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
