package com.logibear;

import com.logibear.api.Api;
import com.logibear.console.DefaultConsole;

/**
 * <p>Opens a restful json api, that supports/
 * works with logical expressions.</p>
 * @author Jan-Philip Richter, Stephan Strate
 * @since 1.0.0
 */
public class Main {

    /**
     * <p>Main method. Initialises restful json
     * api and the console.</p>
     * @param args  arguments (unused)
     * @since 1.0.0
     */
    public static void main (String[] args) {
        // open restful json api
        Api api = new Api();
        // init endpoints
        api.init();

        // start default console
        DefaultConsole defaultConsole = new DefaultConsole();
        defaultConsole.start();
    }
}