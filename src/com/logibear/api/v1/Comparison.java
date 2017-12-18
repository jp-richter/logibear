package com.logibear.api.v1;

import com.google.gson.Gson;
import com.logibear.api.Endpoint;
import com.logibear.api.v1.gson.ComparisonResult;
import com.logibear.parser.Calculator;
import com.logibear.sql.tables.log.Request;
import spark.Response;

/**
 * <p>Defines endpoint that's comparing two logical
 * terms and results in a boolean value.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class Comparison extends Endpoint {

    private Calculator calculator = new Calculator();
    private Request request = new Request();

    private Gson gson = new Gson();

    /**
     * <p>Defining the needed parameters by calling super
     * constructor.</p>
     * @param endpoint  endpoint verb
     * @since 1.0.0
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
     * @since 1.0.0
     */
    @Override
    public Object handle(spark.Request request, Response response) throws Exception {
        // track time
        long start = System.currentTimeMillis();

        // get params
        String term1 = request.params("term1");
        String term2 = request.params("term2");

        // get/create the result
        boolean result = calculator.isEquivalent(term1, term2);
        ComparisonResult comparisonResult = new ComparisonResult(term1, term2, result);

        // stop time
        long time = System.currentTimeMillis() - start;
        // log result into database
        this.request.insert("comparison", gson.toJson(comparisonResult), time);

        // render the result
        return render(200, comparisonResult, response);
    }
}