package com.logibear.console;

import com.logibear.sql.tables.log.Error;

/**
 * <p>Default console, thats responsible for
 * major console features like statistics, error
 * logging or manual execution.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class DefaultConsole extends Console {

    /**
     * <p>error function. You can access all errors at
     * the same time with 'error all' or access a specific
     * number of errors with 'error last [amount (optional)]'.</p>
     * @param args  arguments
     * @since 1.0.0
     */
    @Method
    protected void error (String[] args) {
        if (args != null && args[0] != null) {
            // access to error table in log.db
            Error error = new Error();

            // looking for arguments
            switch (args[0]) {
                // show all errors
                case "all": {
                    error.showAll();
                    break;
                }

                // show a specific amount of errors
                case "last": {
                    if (args[1] != null) {
                        try {
                            // parse input number
                            error.showLast(Integer.parseInt(args[1]));
                        } catch (NumberFormatException e) {
                            System.out.println("Second parameter of 'error last [amount (optional)]' needs to be a number.");
                        }
                    } else {
                        // show only the last error
                        error.showLast(1);
                    }
                    break;
                }

                // catch every other argument
                default: {
                    System.out.println("Available methods for 'error' are 'error all' and 'error last [amount (optional)]'.");
                }
            }
        }
    }

    // @TODO: Add more console methods
}