package com.logibear.sql.tables;

import com.logibear.sql.Table;
import com.logibear.sql.databases.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p></p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Test extends Table {

    /**
     * <p></p>
     * @since 1.0.0
     */
    public Test () {
        super("test", new Log(),
                "CREATE TABLE IF NOT EXISTS test (\n" +
                        " id integer PRIMARY KEY,\n" +
                        " name text NOT NULL,\n" +
                        " ms integer NOT NULL\n" +
                        ");");
    }

    /**
     * <p></p>
     * @param name
     * @param ms
     * @since 1.0.0
     */
    public void insert (String name, int ms) {
        String sql =
                "INSERT INTO test(name,ms) VALUES(?,?)";
        try {
            // get connection
            Connection connection = getDatabase().getConnection();

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, ms);

            // execute statement and close connection
            preparedStatement.executeUpdate();
            getDatabase().closeConnection();
        } catch (SQLException e) {
            System.out.println("Can not execute statement.");
        }
    }
}