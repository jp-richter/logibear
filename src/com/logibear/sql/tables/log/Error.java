package com.logibear.sql.tables.log;

import com.logibear.sql.Table;
import com.logibear.sql.databases.Log;
import java.sql.*;

/**
 * <p>Represents the Sqlite table error, which
 * can be used to log errors and categorize them
 * by section. Error log can be requested and
 * printed in {@see DefaultConsole}.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Error extends Table {

    /**
     * <p>Just opens a connection to the
     * table, no new line will be inserted.</p>
     * @since 1.0.0
     */
    public Error () {
        this(null, null);
    }

    /**
     * <p>Inserts a new row with section
     * "default".</p>
     * @param message   inserted message
     * @since 1.0.0
     */
    public Error (String message) {
        this("default", message);
    }

    /**
     * <p>Creates sql table/opens connection and
     * can insert a new row.</p>
     * @param section   selected section
     * @param message   inserted message
     * @since 1.0.0
     */
    public Error (String section, String message) {
        super("error", new Log(),
                "CREATE TABLE IF NOT EXISTS error (\n" +
                        " id integer PRIMARY KEY,\n" +
                        " section text NOT NULL,\n" +
                        " message text NOT NULL,\n" +
                        " timestamp text NOT NULL\n" +
                        ");");

        // only insert message when message and section is given
        if (section != null && message != null) {
            insert(section, message);
        }
    }

    /**
     * <p>Inserts a new row to the given table,
     * opens and closes the database connection
     * automatically.</p>
     * @param section
     * @param message
     * @since 1.0.0
     */
    public void insert (String section, String message) {
        String sql =
                "INSERT INTO error(section,message,timestamp) VALUES(?,?,datetime('now', 'localtime'))";
        try {
            // get connection
            Connection connection = getDatabase().getConnection();

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, section);
            preparedStatement.setString(2, message);

            // execute statement and close connection
            preparedStatement.executeUpdate();
            getDatabase().closeConnection();
        } catch (SQLException e) {
            System.out.println("Can not execute statement.");
        }
    }

    // @TODO: Add methods to get errors by section/time etc.
}