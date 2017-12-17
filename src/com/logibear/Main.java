package com.logibear;

import com.logibear.api.Api;

/**
 * Created by Jan on 06.12.2017.
 */
public class Main {

    public static void main( String[] args ) {
        // open restful api
        Api api = new Api();
        // init endpoints
        api.init();
    }
}