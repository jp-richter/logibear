package com.logibear.sql.tables.user;

import com.logibear.sql.Table;
import com.logibear.sql.databases.User;

public class Customer extends Table {

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
}