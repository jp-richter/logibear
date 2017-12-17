package com.logibear.parser;

/**
 * Created by Jan on 08.12.2017.
 */

public class Test_ClassCalculator {

    public static void main( String[] args ) {

        System.setOut(System.err);

        Calculator calc = new Calculator();

        String test1 = "";
        String test4 = "|";
        String test2 = "A";
        String test3 = "A | B";
        String test5 = "A -> B";
        String test6 = "A & B";

        String[] arr = {test1, test2, test3, test4, test5, test6 };

        System.out.println( "Test for string: " + test2 + ", " +  test3 );

        try {
            boolean result = calc.isEquivalent( test2, test3 );
            System.out.println( "Result: " + result );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------------");
    }
}
