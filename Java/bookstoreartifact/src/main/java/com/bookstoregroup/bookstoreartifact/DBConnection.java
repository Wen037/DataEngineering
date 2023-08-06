package com.bookstoregroup.bookstoreartifact;

import com.zaxxer.hikari.*;
import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bookstore");
        config.setUsername("root");
        config.setPassword("root");

        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        config.setMinimumIdle(2);

        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
