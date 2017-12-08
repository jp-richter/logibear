package com.logibear.api;

import static spark.Spark.*;

public class Api {

    public Api () {

    }

    public void start () {
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

    private boolean comparison (String term1, String term2) {
        return true;
    }
}