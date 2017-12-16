import java.util.LinkedList;

/**
 * Created by Jan on 08.12.2017.
 *
 * TODO write enum for tokens/sequences
 * TODO if variables are not assigned return variable?
 */

public class ExpressionTree {

    private LinkedList<ExpressionTree> children;
    private Token token;

    public ExpressionTree( Token token ) {
        children = new LinkedList<>();
        this.token = token;
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

    public void assignVariable( String var, boolean value ) {
        if( token.sequence.equals( var ) ) {
            if( value ) {
                token.sequence = "1";
            }
            else {
                token.sequence = "0";
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
                return false;

            case "1":
                return true;

            case "&":
                for( ExpressionTree c : children ) {
                    if( !c.evaluate() ) {
                        return false;
                    }
                }
                return true;

            case "|":
                for( ExpressionTree c : children ) {
                    if( c.evaluate() ) {
                        return true;
                    }
                }
                return false;

            case "->":
                boolean accumulator = true;
                LinkedList<ExpressionTree> stack = (LinkedList<ExpressionTree>)children.clone(); //safe cast
                while( !stack.isEmpty() ) {
                    accumulator = !accumulator || stack.getFirst().evaluate();
                    stack.pop();
                }
                return accumulator;

            default:
                throw new IllegalStateException("Valid token expected, but " +token.sequence+ " found instead");
        }
    }
}
