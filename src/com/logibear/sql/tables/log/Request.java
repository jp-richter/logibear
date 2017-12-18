package com.logibear.sql.tables.log;

import com.logibear.sql.Table;
import com.logibear.sql.databases.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Request extends Table {

    public Request () {
        this(null, null, 0);
    }

    public Request (String endpoint, String result, long time) {
        super("request", new Log(),
                "CREATE TABLE IF NOT EXISTS request (\n" +
                        " id integer PRIMARY KEY,\n" +
                        " endpoint text NOT NULL,\n" +
                        " result text NOT NULL,\n" +
                        " time integer NOT NULL,\n" +
                        " timestamp text NOT NULL\n" +
                        ");");

        if (endpoint != null && result != null && time != 0) {
            insert(endpoint, result, time);
        }
    }

    public void insert (String endpoint, String result, long time) {
        String sql =
                "INSERT INTO request(endpoint,result,time,timestamp) VALUES(?,?,?,datetime('now', 'localtime'))";
        try {
            // get connection
            Connection connection = getDatabase().getConnection();

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, endpoint);
            preparedStatement.setString(2, result);
            preparedStatement.setLong(3, time);

            // execute statement and close connection
            preparedStatement.executeUpdate();
            getDatabase().closeConnection();
        } catch (SQLException e) {
            System.out.println("Can not execute statement.");
        }
    }
}