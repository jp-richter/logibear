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
        ExpressionTree clone = new ExpressionTree( new Token( token.token, token.sequence ) );
        if( negated ) {
            clone.negate();
        }
        for( ExpressionTree c : children ) {
            ExpressionTree child = c.clone();
            if( c.negated ) {
                child.negate();
            }
            clone.addChild( child );
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
                token = new Token( token.token, value );
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
                boolean accumulator1 = true;
                LinkedList<ExpressionTree> stack1 = new LinkedList<>();
                stack1.addAll( children );
                while( !stack1.isEmpty() ) {
                    accumulator1 = accumulator1 && stack1.getFirst().evaluate();
                    stack1.pop();
                }
                if( negated ) {
                    return !accumulator1;
                }
                return accumulator1;

            case "|":
                for( ExpressionTree c : children ) {
                    if( c.evaluate() ) {
                        return !negated;
                    }
                }
                return negated;

            case "->":
                boolean accumulator2 = true;
                LinkedList<ExpressionTree> stack2 = new LinkedList<>();
                stack2.addAll( children );
                while( !stack2.isEmpty() ) {
                    accumulator2 = !accumulator2 || stack2.getFirst().evaluate();
                    stack2.pop();
                }
                if( negated ) {
                    return !accumulator2;
                }
                return accumulator2;

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
