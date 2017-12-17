package com.logibear.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>Helps to create several databases in different
 * folders. Easy to use/implements abstract class.
 * Just extend it and call the super class. You can
 * get connections and close them again with this class.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public abstract class Database {

    private final String path = ".logidb/";

    private Connection con = null;
    private String url;

    /**
     * <p>Creates a Sqlite database, creating
     * the needed path and database itself.</p>
     * @param database  database name
     * @param path      path extension
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
     * <p>Creates the folders, that are given
     * to store the databases in it.</p>
     * @param ext   path extension
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
     * <p>Opens a new database connection.</p>
     * @return  database connection
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
     * <p>Closes a open database connection.</p>
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