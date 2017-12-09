package com.logibear.api;

import static spark.Spark.port;
import com.logibear.api.v1.Comparison;

/**
 * <p>Provides a restful json api, that's easy to
 * configure and easy to use.<br />
 * Example local usage: http://localhost:4567/[endpoint]/[arguments]</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Api {

    /**
     * <p>Initialize restful api with port and
     * location.</p>
     * @since 1.0.0
     */
    public Api () {
        // you can setup the port right here
        port(4567);
    }

    /**
     * <p>Setup the api routes.<br />
     * Routes:<br />
     * comparison, compare two terms</p>
     * @since 1.0.0
     */
    public void start () {
        // @TODO: Fix logging error message (https://www.slf4j.org/codes.html#StaticLoggerBinder)
        System.out.println("You can ignore the SLF4J logging errors.");

        Endpoint comparison = new Comparison("v1/comparison");
    }
}