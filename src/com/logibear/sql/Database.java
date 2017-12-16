package com.logibear.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p></p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public abstract class Database {

    private final String path = ".logidb/";

    private Connection con = null;
    private String url;

    /**
     * <p></p>
     * @param database
     * @param path
     * @since 1.0.0
     */
    public Database (String database, String path) {
        try {
            createFolder(path);
            // prepare url
            url = "jdbc:sqlite:" + this.path + path + database;

            // get database connection (create database if needed)
            con = DriverManager.getConnection(url);
            // close connection
            con.close();
        } catch (SQLException e) {
            System.out.println("Can not create sql " + this.path + path + database);
        }
    }

    /**
     * <p></p>
     * @param ext
     * @since 1.0.0
     */
    private void createFolder (String ext) {
        // create path as file
        File file = new File(path + ext);

        // if file not exists
        if (!file.exists()) {
            // create folder
            file.mkdir();
        }
    }

    /**
     * <p></p>
     * @return
     * @since 1.0.0
     */
    public Connection getConnection () {
        // reset connection
        con = null;
        try {
            // open connection
            return con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Can not open sql " + url);
        }

        // return null when error occurs
        return con;
    }

    /**
     * <p></p>
     * @since 1.0.0
     */
    public void closeConnection () {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Can not close connection.");
        }
    }
}