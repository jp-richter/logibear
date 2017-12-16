package com.logibear.sql;

import java.sql.*;

/**
 * <p></p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public abstract class Table {

    private String name;
    private Database database;

    /**
     * <p></p>
     * @param name
     * @param database
     * @param sql
     * @since 1.0.0
     */
    public Table (String name, Database database, String sql) {
        this.name = name;
        this.database = database;

        try {
            // get connection
            Connection connection = database.getConnection();

            // execute statement
            Statement statement = connection.createStatement();
            statement.execute(sql);

            // close connection
            database.closeConnection();
        } catch (SQLException e) {
            System.out.println("Can not create sql statement.");
        }
    }

    /**
     * <p></p>
     * @since 1.0.0
     */
    public void showAll () {
        String sql =
                "SELECT * FROM " + name;

        try {
            // get connection
            Connection connection = database.getConnection();

            // execute statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // get columns number
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();

            // print whole database
            System.out.println("Printing " + name + ":");
            while (resultSet.next()) {
                System.out.println();
                for (int i = 1; i < (columnsNumber + 1); i++) {
                    System.out.print(resultSet.getString(i) + "  ");
                }
            }

            // close connection
            database.closeConnection();
        } catch (SQLException e) {
            System.out.println("Can not show elements of database " + name);
        }
    }

    /**
     * <p></p>
     * @return
     * @since 1.0.0
     */
    public String getName() {
        return name;
    }

    /**
     * <p></p>
     * @return
     * @since 1.0.0
     */
    public Database getDatabase() {
        return database;
    }
}