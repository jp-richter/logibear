package com.logibear.api;

import com.logibear.api.v1.Comparison;

import static spark.Spark.*;

/**
 * <p>Provides a restful json api, that's easy to
 * configure and easy to use.<br />
 * Example local usage: http://localhost:80/[endpoint]/[arguments]</p>
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
        port(80);

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

        // check if api key is valid
        before("api/*", (request, response) -> {
            response.type("application/json");
            response.header("Content-Encoding", "gzip");
            response.header("Cache-Control", "max-age=604800");
            halt(403, "{\"status\":{\"code\":403,\"message\":\"Forbidden\"},\"message\":null}");
        });

        // api path
        path("api/", () -> {
            // v1
            path("v1/", () -> {
                Endpoint comparison = new Comparison("comparison");
            });

            // v2
            path("v2/", () -> {

            });
        });
    }
}