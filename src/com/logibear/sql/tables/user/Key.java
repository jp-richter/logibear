package com.logibear.sql.tables.user;

import com.logibear.sql.Table;
import com.logibear.sql.databases.User;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * <p></p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Key extends Table {

    /**
     * <p></p>
     * @since 1.0.0
     */
    public Key () {
        super("key", new User(),
                "CREATE TABLE IF NOT EXISTS key (\n" +
                        " id integer PRIMARY KEY,\n" +
                        " customer integer NOT NULL,\n" +
                        " api_key text NOT NULL,\n" +
                        " current_requests integer NOT NULL,\n" +
                        " total_requests integer NOT NULL,\n" +
                        " refill_interval integer NOT NULL,\n" +
                        " refill_last text NOT NULL,\n" +
                        " timestamp text NOT NULL\n" +
                        ");");
    }

    /**
     * <p></p>
     * @param customer
     * @param total_requests
     * @param refill_interval
     * @since 1.0.0
     */
    public void insert (int customer, int total_requests, int refill_interval) {
        String sql =
                "INSERT INTO key(customer,api_key,current_requests,total_requests,refill_interval,refill_last,timestamp) VALUES(?,?,?,?,?,datetime('now', 'localtime'),datetime('now', 'localtime'))";
        try {
            // get connection
            Connection connection = getDatabase().getConnection();

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer);
            preparedStatement.setString(2, generateUniqueKey());
            preparedStatement.setInt(3, total_requests);
            preparedStatement.setInt(4, total_requests);
            preparedStatement.setInt(5, refill_interval);

            // execute statement and close connection
            preparedStatement.executeUpdate();
            getDatabase().closeConnection();
        } catch (SQLException e) {
            System.out.println("Can not execute statement.");
        }
    }

    /**
     * <p></p>
     * @return
     * @since 1.0.0
     */
    private String generateUniqueKey () {
        // create random id
        String api_key = UUID.randomUUID().toString();

        String sql =
                "SELECT id FROM key WHERE api_key = '" + api_key + "'";

        try {
            Connection connection = getDatabase().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // if api_key already exist
            if (resultSet.next()) {
                // generate new one
                return generateUniqueKey();
            }
        } catch (SQLException e) {
            System.out.println("Can not execute statement.");
        }

        return api_key;
    }

    /**
     * <p></p>
     * @param api_key
     * @return
     * @since 1.0.0
     */
    public boolean enter (String api_key) {
        String sql =
                "SELECT * FROM key WHERE api_key = '" + api_key + "'";

        try {
            // get row of api key
            Connection connection = getDatabase().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                // api key is not in database (not valid)
                getDatabase().closeConnection();
                return false;
            }

            // @TODO: improve fairness for api (check refill interval before current_requests)

            if (resultSet.getInt("total_requests") == -1) {
                // total_requests is a wild card (unlimited access)
                getDatabase().closeConnection();
                return true;
            } else if (resultSet.getInt("current_requests") > 0) {
                // customer has requests left, decrease current_requests
                sql = "UPDATE key SET current_requests = " + (resultSet.getInt("current_requests") - 1) + " WHERE api_key = '" + api_key + "'";
                statement.execute(sql);
                getDatabase().closeConnection();
                return true;
            } else {
                // no requests left, check if refill interval is expired
                try {
                    DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS", Locale.ROOT);
                    java.util.Date date = format.parse(resultSet.getString("refill_last"));

                    java.util.Date now = format.parse(format.format(Calendar.getInstance().getTime()));

                    if ((now.getTime() - date.getTime()) >= resultSet.getInt("refill_interval")) {
                        // refill_interval is expired
                        sql = "UPDATE key SET current_requests = " + (resultSet.getInt("total_requests") - 1) + ", refill_last = datetime('now', 'localtime') WHERE api_key = '" + api_key + "'";
                        statement.execute(sql);
                        getDatabase().closeConnection();
                        return true;
                    } else {
                        // refill_interval is not yet expired
                        getDatabase().closeConnection();
                        return false;
                    }
                } catch (ParseException e) {
                    System.out.println("Can not parse date.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Can not execute statement.");
        }

        // any exception ends up in false
        getDatabase().closeConnection();
        return false;
    }

    // @TODO: add key related methods
    // @TODO: improve commenting
}