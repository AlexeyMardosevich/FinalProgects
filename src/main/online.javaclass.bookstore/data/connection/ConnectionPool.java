/*
package data.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private static final int POOL_SIZE = 16;
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private final ProxyConnection<ProxyConnection> realConnection;

    ConnectionPool(String driver, String url, String user, String password) {
        realConnection = new LinkedBlockingDeque<>(POOL_SIZE);
        try {
            Class.forName(driver);
            log.info("Database driver loader");
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                realConnection.offer(new ProxyConnection(connection, this));
                log.info("Connection created");
            }
        } catch (SQLException | CloneNotSupportedException e) {
            log.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        try {
            return realConnection.close;
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
*/
