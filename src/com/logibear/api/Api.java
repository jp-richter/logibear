package com.logibear.api;

import com.logibear.api.v1.Comparison;
import com.logibear.sql.tables.user.Key;

import static spark.Spark.*;

/**
 * <p>Provides a restful json api, that's easy to
 * configure and easy to use.<br />
 * Example local usage: http://localhost:80/[endpoint]/[arguments]</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Api {

    private Key key = new Key();

    /**
     * <p>Initialize restful api with port and
     * location.</p>
     * @since 1.0.0
     */
    public Api () {
        // you can setup the port right here
        port(81);

        // custom not found exception
        notFound((request, response) ->
            "{\"status\":{\"code\":404,\"message\":\"Not found\"},\"message\":null}"
        );

        // custom internal server error exception
        internalServerError((request, response) ->
            "{\"status\":{\"code\":500,\"message\":\"Internal Server Error\"},\"message\":null}"
        );
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

        // add specific things before api is called
        before("api/*", (request, response) -> {
            // define response header (always json)
            response.type("application/json");
            response.header("Content-Encoding", "gzip");
            response.header("Cache-Control", "max-age=604800");

            // check api key
            String api_key;
            if ((api_key = request.queryParams("api_key")) != null) {
                if (!key.enter(api_key)) {
                    halt(403, "{\"status\":{\"code\":403,\"message\":\"Forbidden\"},\"message\":null}");
                }
            } else {
                halt(403, "{\"status\":{\"code\":403,\"message\":\"Forbidden\"},\"message\":null}");
            }
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