package com.logibear.parser;

import java.util.LinkedList;

/**
 * Created by Jan on 08.12.2017.
 *
 * TODO write enum for tokens/sequences
 * TODO write method that reduces the tree
 */

public class ExpressionTree {

    private LinkedList<ExpressionTree> children;
    private Token token;
    private boolean negated;

    public ExpressionTree( Token token ) {
        children = new LinkedList<>();
        this.token = token;
        negated = false;
    }

    public void addChild( ExpressionTree node ) {
        children.add( node );
    }

    private boolean isLeaf() {
        return children.isEmpty();
    }

    public Token getToken() {
        return token;
    }

    public void negate() {
        negated = true;
    }

    @Override
    public ExpressionTree clone() {
        ExpressionTree clone = new ExpressionTree( token );
        for( ExpressionTree c : children ) {
            clone.addChild( c );
            c.clone();
        }
        return clone;
    }

    public String toString() {
        StringBuilder b = new StringBuilder(token.sequence + ", ");
        if( !isLeaf() ) {
            b.append(" (");
            for( ExpressionTree n : children ) {
                b.append( n.toString() );
            }
            b.append(") ");
        }
        return b.toString();
    }

    public void assignVariable( String var, String value ) {
        if( token.sequence.equals( var ) ) {
            if( value.equals("0") || value.equals("1")) {
                token.sequence = value;
            }
            else {
                throw new IllegalArgumentException("'0' or '1' expected, but " +value+ " found instead");
            }
        }
        else {
            for( ExpressionTree c : children ) {
                c.assignVariable( var, value );
            }
        }
    }

    @SuppressWarnings("unchecked")
    public boolean evaluate() {
        switch( token.sequence ) {

            case "0":
                return negated;

            case "1":

                return !negated;

            case "&":
                for( ExpressionTree c : children ) {
                    if( !c.evaluate() ) {
                        return negated;
                    }
                }
                return !negated;

            case "|":
                for( ExpressionTree c : children ) {
                    if( c.evaluate() ) {
                        return !negated;
                    }
                }
                return negated;

            case "->":
                boolean accumulator = true;
                LinkedList<ExpressionTree> stack = (LinkedList<ExpressionTree>)children.clone(); //safe cast
                while( !stack.isEmpty() ) {
                    accumulator = !accumulator || stack.getFirst().evaluate();
                    stack.pop();
                }
                if( negated ) {
                    return !accumulator;
                }
                return accumulator;

            default:
                throw new IllegalStateException("Valid token expected, but " +token.sequence+ " found instead");
        }
    }

    public LinkedList<String> getVariables() {
        LinkedList<String> variables = new LinkedList<>();
        if( token.token == Token.VARIABLE ) {
            variables.add( token.sequence );
        }
        for( ExpressionTree c : children ) {
            variables.addAll( c.getVariables() );
        }
        return variables;
    }
}
