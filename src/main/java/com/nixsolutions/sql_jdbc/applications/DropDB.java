package com.nixsolutions.sql_jdbc.applications;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DropDB {
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "";
    private static final Logger logger = LogManager.getLogger(DropDB.class);

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = null;
        StringBuilder lineFromFile = new StringBuilder();
        try {
            File file = new File(
                    "./src/main" + File.separator
                            + "resources" + File.separator + "dropDB.sql");
            if (!file.exists()) {
                throw new IllegalArgumentException();
            }
            String line;
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                lineFromFile.append(line);
            }
            Connection connection = DriverManager
                    .getConnection(url, user, password);
            PreparedStatement preparedStatement = connection
                    .prepareStatement(lineFromFile.toString());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
    }
}
