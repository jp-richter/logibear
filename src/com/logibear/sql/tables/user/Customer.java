package com.logibear.sql.tables.user;

import com.logibear.sql.Table;
import com.logibear.sql.databases.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p></p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Customer extends Table {

    /**
     * <p></p>
     * @since 1.0.0
     */
    public Customer () {
        super("customer", new User(),
                "CREATE TABLE IF NOT EXISTS customer (\n" +
                        " id integer PRIMARY KEY,\n" +
                        " company text NOT NULL,\n" +
                        " firstname text NOT NULL,\n" +
                        " lastname text NOT NULL,\n" +
                        " birthday text NOT NULL,\n" +
                        " timestamp text NOT NULL\n" +
                        ");");
    }

    /**
     * <p></p>
     * @param company
     * @param firstname
     * @param lastname
     * @param birthday
     * @since 1.0.0
     */
    public void insert (String company, String firstname, String lastname, String birthday) {
        String sql =
                "INSERT INTO customer(company,firstname,lastname,birthday,timestamp) VALUES(?,?,?,?,datetime('now', 'localtime'))";
        try {
            // get connection
            Connection connection = getDatabase().getConnection();

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, company);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, birthday);

            // execute statement and close connection
            preparedStatement.executeUpdate();
            getDatabase().closeConnection();
        } catch (SQLException e) {
            System.out.println("Can not execute statement.");
        }
    }

    // @TODO: add methods related to customers
    // @TODO: improve commenting
}