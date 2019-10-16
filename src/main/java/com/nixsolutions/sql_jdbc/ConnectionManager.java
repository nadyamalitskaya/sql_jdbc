package com.nixsolutions.sql_jdbc;

import org.postgresql.ds.PGPoolingDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionManager {
    private ConnectionManager() {
    }

    private static final Logger logger = LogManager
            .getLogger(ConnectionManager.class);
    private PGPoolingDataSource dataSource;
    private static ConnectionManager instance;

    public static synchronized ConnectionManager getInstance()
            throws IOException {
        if (instance == null) {
            try (InputStream inputStream = ConnectionManager.class
                    .getResourceAsStream("/jdbc.properties")) {
                Properties properties = new Properties();
                properties.load(inputStream);

                String servername = properties.getProperty("serverName");
                int port = Integer.parseInt(properties.getProperty("port"));
                String databaseName = properties.getProperty("databaseName");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                instance = new ConnectionManager();
                instance.dataSource = new PGPoolingDataSource();
                instance.dataSource.setServerName(servername);
                instance.dataSource.setPortNumber(port);
                instance.dataSource.setDatabaseName(databaseName);
                instance.dataSource.setUser(user);
                instance.dataSource.setPassword(password);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
