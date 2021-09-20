package com.company.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector {
    String driverClass = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/acko";
    String username = "root";
    String password = "K@nh@#1994";

    public Connection createConnection() {
        try {
            Class.forName(driverClass);
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
            return connection;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
}
