package by.vadarod.javaee.config;

import java.sql.Connection;

public interface JdbcConnection {

    Connection getConnection();
}
