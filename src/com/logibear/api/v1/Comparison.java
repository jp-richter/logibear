package com.logibear.api.v1;

import com.logibear.api.Endpoint;
import spark.Request;
import spark.Response;

/**
 * <p>Defines endpoint that's comparing two logical
 * terms and results in a boolean value.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Comparison extends Endpoint {

    /**
     * <p>Defining the needed parameters by calling super
     * constructor.</p>
     * @param endpoint  endpoint verb
     */
    public Comparison (String endpoint) {
        super(endpoint + "/:term1/:term2");
    }

    /**
     * <p>Handles the api request. Parameters can be accessed
     * with {@code request.params("term1")} for example.</p>
     * @param request   request object
     * @param response  response object
     * @return  result
     * @throws Exception default
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return render(200, "Successful", response);
    }
}