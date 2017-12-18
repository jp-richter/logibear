package com.logibear.sql.tables.user;

import com.logibear.sql.Table;
import com.logibear.sql.databases.User;

public class Key extends Table {

    public Key () {
        super("key", new User(),
                "CREATE TABLE IF NOT EXISTS key (\n" +
                        " id integer PRIMARY KEY,\n" +
                        " customer integer NOT NULL,\n" +
                        " key text NOT NULL,\n" +
                        " current_requests integer NOT NULL,\n" +
                        " total_requests integer NOT NULL,\n" +
                        " refill_interval integer NOT NULL,\n" +
                        " refill_last text NOT NULL,\n" +
                        " timestamp text NOT NULL\n" +
                        ");");
    }
}