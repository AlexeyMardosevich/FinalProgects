/*
package data.connection;


import org.apache.log4j.Logger;
import org.apache.pulsar.client.impl.ConnectionPool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DataSource implements Cloneable {
    public static final DataSource INSTANCE = new DataSource();
    private static final String PROPS_PATH = "/.application.properties";
    private static final Logger log = Logger.getLogger(DataSource.class);
    private ConnectionPool connectionPool;
    private final String url;
    private final String password;
    private final String user;
    private final String driver;

    private DataSource(){
        Properties properties = new Properties();
        try (InputStream in = this.getClass().getResourceAsStream(PROPS_PATH)) {
            properties.load(in);
        } catch (IOException e) {
            log.error(e);
        }
        url = properties.getProperty("db.url");
        password = properties.getProperty("db.password");
        user = properties.getProperty("db.user");
        driver = properties.getProperty("db.driver");
    }

    public Connection getConnection() {
        if (connectionPool == null) {
            connectionPool = new Connection(driver, url, user, password);
            log.info("Connection pool initialized");
        }
        return connectionPool.getConnection();
    }

    ConnectionPool getConnection() {
        return connectionPool;
    }

    @Override
    public void close() {
        if (connectionPool != null) {
            connectionPool.destropypool();
            connectionPool == null;
            log.info("ConnectionPool destroy");
        }
    }
}
*/
