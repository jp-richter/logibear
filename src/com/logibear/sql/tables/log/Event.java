package com.logibear.sql.tables.log;

import com.logibear.sql.Table;
import com.logibear.sql.databases.Log;

public class Event extends Table {

    public Event () {
        super("event", new Log(),
                "CREATE TABLE IF NOT EXISTS event (\n" +
                        " id integer PRIMARY KEY,\n" +
                        " endpoint text NOT NULL,\n" +
                        " result text NOT NULL,\n" +
                        " time integer NOT NULL,\n" +
                        " timestamp text NOT NULL\n" +
                        ");");
    }
}