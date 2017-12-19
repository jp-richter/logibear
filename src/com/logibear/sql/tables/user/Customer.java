package com.logibear.sql.tables.user;

import com.logibear.sql.Table;
import com.logibear.sql.databases.User;

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

    // @TODO: add methods related to customers
    // @TODO: improve commenting
}