package data.connection;

import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j
public class DatabaseManager {

    private final ProxyConnection connection;

    public DatabaseManager(String url, String password, String login) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, login, password);
            this.connection = new ProxyConnection(connection);
        } catch (ClassNotFoundException e) {
            log.error("PostgreSQL driver not found", e);
            throw new RuntimeException("PostgreSQL driver not found", e);
        } catch (SQLException e) {
            log.error("Failed to connect to database: " + url, e);
            throw new RuntimeException("Failed to connect to database: " + e.getMessage(), e);
        }
    }

    public Connection getconnection() {
        return connection;
    }

    public void close() {
        try {
            connection.reallyClose();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close connection", e);
        }
    }
}
