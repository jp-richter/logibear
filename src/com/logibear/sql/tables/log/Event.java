package com.logibear.sql.tables.log;

import com.logibear.sql.Table;
import com.logibear.sql.databases.Log;

/**
 * <p></p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Event extends Table {

    /**
     * <p></p>
     * @since 1.0.0
     */
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

    // @TODO: Add insert method and other methods that are based on events
    // @TODO: Improve commenting
}