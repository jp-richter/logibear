package com.logibear.api;

import static spark.Spark.*;

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

        get("comparison/:term1/:term2", (request, response) -> {
            response.status(200);
            response.type("application/json");

            boolean status = comparison(request.params(":term1"), request.params(":term2"));
            if (status) {
                return "{\"status\":true,\"terms\":[\"" + request.params(":term1") + "\",\"" + request.params(":term2") + "\"]}";
            } else {
                return "{\"status\":false,\"terms\":[\"" + request.params(":term1") + "\",\"" + request.params(":term2") + "\"]}";
            }
        });
    }

    /**
     * <p>Compare two logical terms.</p>
     * @param term1 term1 to compare
     * @param term2 term2 to compare
     * @return  are terms equal
     * @since 1.0.0
     */
    private boolean comparison (String term1, String term2) {
        // @TODO: Setup comparison
        return true;
    }
}