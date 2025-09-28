package by.vadarod.javaee.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection implements JdbcConnection{
    @Override
    public Connection getConnection() {

        try {
            String url = "jdbc:postgresql://localhost:5432/testdb";
            String username = "postgres";
            String password = "admin";

            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
