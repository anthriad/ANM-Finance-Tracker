package org.yourcompany.yourproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB {
    public static Connection connect() throws SQLException {
        try {
            
          // Get database credentials from DatabaseConfig class
            String jdbcURL = DatabaseConfig.getDbUrl();
            String user = DatabaseConfig.getDbUsername();
            String password = DatabaseConfig.getDbPassword();

            
            return DriverManager.getConnection(jdbcURL, user, password);
        } 
        catch (SQLException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}