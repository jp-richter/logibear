package com.logibear.parser;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Jan on 15.12.2017.
 *
 */

public class Calculator {

    private ExpressionTree left;
    private ExpressionTree right;

    private HashMap<String, String> varAssignments;

    private Tokenizer tok;
    private Parser pars;

    public Calculator() {
        left = null;
        right = null;

        varAssignments = new HashMap<>();

        tok = new Tokenizer();
        initTokenizer();
        pars = new Parser();
    }

    private void initTokenizer() {
        tok.add("[0..1]", 1);
        tok.add("[a-zA-Z]", 2);
        tok.add("\\&", 3);
        tok.add("\\|", 4);
        tok.add("->", 5);
        tok.add("\\(", 6);
        tok.add("\\)", 7);
    }

    public ExpressionTree getLeftTree() {
        return left;
    }

    public ExpressionTree getRightTree() {
        return right;
    }

    public void assignVar( String var, String value ) {
        varAssignments.put( var, value );
    }

    public void clearAssignments() {
        varAssignments.clear();
    }

    public boolean isEquivalent( String left, String right ) throws ParserException {
        this.left = generateTree( left );
        this.right = generateTree( right );

        LinkedList<String> vars = this.left.getVariables();
        vars.addAll( this.right.getVariables() );

        int varCount = vars.size();
        int length = (int)Math.pow(2, varCount) + 1;
        String[][] possibilities = new String[length][varCount];

        int count = 0;
        for( int i = 0; i < length; i++ ) {
            String bin = Integer.toBinaryString( count );
            for( int j = bin.length(); j < varCount; j++ ) {
                bin = "0" + bin;
            }

            int index = 0;

            for( int j = 0; j < varCount; j++ ) {
                if( i == 0 ) {
                    possibilities[0][j] = vars.get( j );
                }
                else {
                    String number = Character.toString( bin.charAt( index ) );
                    possibilities[i][j] = number;

                    index++;
                }
            }

            if( i != 0 ) {
                count++;
            }
        }

        for( int i = 1; i < length; i++ ) {
            ExpressionTree tempLeft = this.left.clone();
            ExpressionTree tempRight = this.right.clone();

            for( int j = 0; j < varCount; j++ ) {
                tempLeft.assignVariable( possibilities[0][j], possibilities[i][j] );
                tempRight.assignVariable( possibilities[0][j], possibilities[i][j] );
            }
            
            if( tempLeft.evaluate() != tempRight.evaluate() ) {
                return false;
            }
        }

        return true;
    }

    private ExpressionTree generateTree( String expression ) throws ParserException {
        tok.tokenize( expression );
        return pars.parse( tok.getTokens() );
    }
}
