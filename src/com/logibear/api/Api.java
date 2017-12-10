package com.logibear.api;

import static spark.Spark.port;
import static spark.Spark.notFound;
import static spark.Spark.internalServerError;
import static spark.Spark.path;
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

        // custom not found exception
        notFound((request, response) -> {
            response.type("application/json");
            response.header("Content-Encoding", "gzip");
            response.header("Cache-Control", "max-age=604800");
            return "{\"status\":{\"code\":404,\"message\":\"Not found\"},\"message\":null}";
        });

        // custom internal server error exception
        internalServerError((request, response) -> {
            response.type("application/json");
            response.header("Content-Encoding", "gzip");
            response.header("Cache-Control", "max-age=604800");
            return "{\"status\":{\"code\":500,\"message\":\"Internal Server Error\"},\"message\":null}";
        });
    }

    /**
     * <p>Setup the api routes.<br />
     * Routes:<br />
     * comparison, compare two terms</p>
     * @since 1.0.0
     */
    public void init () {
        // @TODO: Fix logging error message (https://www.slf4j.org/codes.html#StaticLoggerBinder)
        System.out.println("You can ignore the SLF4J logging errors.");

        path("v1/", () -> {
            Endpoint comparison = new Comparison("comparison");
        });
    }
}