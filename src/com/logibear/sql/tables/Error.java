package com.logibear.sql.tables;

import com.logibear.sql.Table;
import com.logibear.sql.databases.Log;

import java.sql.*;

/**
 * <p></p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Error extends Table {

    /**
     * <p></p>
     * @since 1.0.0
     */
    public Error () {
        this(null, null);
    }

    public Error (String message) {
        this("default", message);
    }

    /**
     * <p></p>
     * @param section
     * @param message
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

        if (section != null && message != null) {
            insert(section, message);
        }
    }

    /**
     * <p></p>
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

    /**
     * <p></p>
     * @param rows
     * @since 1.0.0
     */
    public void showLast (int rows) {
        String sql =
                "SELECT * FROM (SELECT * FROM error ORDER BY id DESC LIMIT " + rows + ") ORDER BY id ASC";

        try {
            // get connection
            Connection connection = getDatabase().getConnection();

            // execute statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // get columns number
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();

            // print last i rows
            int i = rows;
            while (resultSet.next() && i > 0) {
                System.out.println();
                for (int x = 1; x < (columnsNumber + 1); x++) {
                    System.out.print(resultSet.getString(x) + "  ");
                }

                i--;
            }
        } catch (SQLException e) {
            System.out.println("Can not show elements of database " + getName());
        }
    }
}