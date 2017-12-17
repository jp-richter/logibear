package com.logibear.api.v1.gson;

/**
 * <p>Represents the result of a comparison
 * of two terms. This class is responsible/used to
 * format a json string from it.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class ComparisonResult {

    private String term1;
    private String term2;
    private boolean result;

    /**
     * <p>Initializes a result of a comparison.
     * Can be used to format a json string.</p>
     * @param term1     first term
     * @param term2     second term
     * @param result    result
     * @since 1.0.0
     */
    public ComparisonResult(String term1, String term2, boolean result) {
        this.term1 = term1;
        this.term2 = term2;
        this.result = result;
    }
}