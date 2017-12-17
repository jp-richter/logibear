package com.logibear.console;

import com.logibear.sql.tables.Error;

/**
 * <p>Default console, thats responsible for
 * major console features like statistics, error
 * logging or manual execution.</p>
 * @author Stephan Strate
 * @since 1.0.0
 */
public class DefaultConsole extends Console {

    @Method
    protected void error (String[] args) {
        if (args != null && args[0] != null) {
            Error error = new Error();

            switch (args[0]) {
                case "all": {
                    error.showAll();
                    break;
                }

                case "rows": {
                    if (args[1] != null) {
                        try {
                            error.showLast(Integer.parseInt(args[1]));
                        } catch (NumberFormatException e) {
                            System.out.println("Second parameter of 'error rows [amount]' needs to be a number.");
                        }
                    }
                    break;
                }

                default: {
                    System.out.println("Available methods for 'error' are 'error all' and 'error rows [amount]'.");
                }
            }
        }
    }
}