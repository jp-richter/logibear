package com.logibear.sql.databases;

import com.logibear.sql.Database;

/**
 * <p>Represents the user.db database. Powered
 * by Sqlite.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class User extends Database {

    /**
     * <p>Creates user.db in the default path, without
     * any extensions.</p>
     * @since 1.0.0
     */
    public User () {
        super("user.db");
    }
}