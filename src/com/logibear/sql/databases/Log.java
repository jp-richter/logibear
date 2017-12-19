package com.logibear.sql.databases;

import com.logibear.sql.Database;

/**
 * <p>Represents the log.db database. Powered
 * by Sqlite.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Log extends Database {

    /**
     * <p>Creates log.db in the default path, without
     * any extensions.</p>
     * @since 1.0.0
     */
    public Log () {
        super("log.db");
    }
}