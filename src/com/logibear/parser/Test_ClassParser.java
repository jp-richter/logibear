package com.logibear.parser;

import java.util.LinkedList;

/**
 * Created by Jan on 14.12.2017.
 * Test class for the Parser unit.
 * Assumes Tokenizer and test classes are working correctly.
 */

public class Test_ClassParser {

    public static void main( String[] args ) {
        Tokenizer t = new Tokenizer();

        t.add("[0..1]", 1);
        t.add("[a-zA-Z]", 2);
        t.add("\\&", 3);
        t.add("\\|", 4);
        t.add("->", 5);
        t.add("\\(", 6);
        t.add("\\)", 7);

        String test1 = "";
        String test2 = "A";
        String test3 = "A & B";
        String test4 = "A | B";
        String test5 = "A & B | C -> D";
        String test11 = "A -> B | C & D";
        String test12 = "A | B -> C & D";

        String test13 = "A & (B | C)";
        String test14 = "B | A & (E -> C)";

        String test6 = "A A";
        String test7 = "& &";
        String test8 = "A |";
        String test9 = "| A";
        String test10 = "|";

        String[] arr = {test1, test2, test3, test4, test5, test11, test12, test13, test14,
                test6, test7, test8, test9, test10 };

        Parser p = new Parser();
        System.setOut(System.err);

        for( String s : arr ) {

            try {
                System.out.println( "Test for string: " + s );

                t.tokenize( s );
                LinkedList<Token> l = t.getTokens();
                ExpressionTree tree = p.parse(l);

                System.out.println( "Result: " + tree.toString() );
            }
            catch( Exception e ) {
                e.printStackTrace();
            }

            System.out.println("--------------------------------------------------");
        }
    }
}
