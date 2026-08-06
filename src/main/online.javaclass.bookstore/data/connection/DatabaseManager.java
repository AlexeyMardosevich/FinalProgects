package data.connection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final Logger log = LogManager.getLogger(DatabaseManager.class);
    private final ProxyConnection connection;

    public DatabaseManager(String URL, String PASSWORD, String LOGIN) {
        try {
        Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        this.connection = new ProxyConnection(connection);
    } catch (SQLException e){
        throw new RuntimeException();
        }
    }

    public  Connection getconnection(){
        return connection;
    }

    public void close(){
        try {
        connection.reallyClose();
    }catch (SQLException e){
        throw new RuntimeException();
        }
    }
}
