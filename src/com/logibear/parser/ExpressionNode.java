/**
 * Created by Jan on 14.12.2017.
 * Values are tokens; to avoid confusion with this.value and value
 * Tokens recieved as arguments are referred with 'tok'.
 *
 * TODO Restructure to allow different tree entry points
 *
 */

public class ExpressionNode {

    private ExpressionNode root;
    private ExpressionNode left;
    private ExpressionNode right;

    private Token value;

    public ExpressionNode() {
        root = this;

        left = null;
        right = null;
        value = null;
    }

    private ExpressionNode( ExpressionNode root ) {
        this.root = root;

        left = null;
        right = null;
        value = null;
    }

    public void add( Token tok ) {
        switch( tok.token ) {

            case Token.AND_OR:
                if ( left == null ) {
                    throw new IllegalStateException("Boolean or variable expected, " +
                            "found " + tok.sequence + " instead");
                }
                else if( value == null ) {
                    value = tok;
                }
                else {
                    ExpressionNode temp = right;
                    right = new ExpressionNode( root );
                    right.setValue( tok );
                    right.setLeft( temp.getValue() );
                    //current = right;
                }
                break;

            case Token.BOOLEAN:
            case Token.VARIABLE:
                ExpressionNode node = new ExpressionNode( root );
                node.setValue( tok );
                if( left == null ) {
                    left = node;
                }
                else if( right == null ) {
                    right = node;
                }
                else {
                    throw new IllegalStateException("Operator expected, " +
                            "found " + tok.sequence + " instead");
                }
                break;

            default:
                throw new IllegalArgumentException("Boolean, variable or operator token expected, " +
                        "found " + tok.sequence + " instead");
        }
    }

    private void setLeft( Token tok ) {
        left = new ExpressionNode( root );
        left.setValue( tok );
    }

    private void setValue( Token tok ) {
        value = tok;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    public Token getValue() {
        return value;
    }

    public ExpressionNode getRoot() {
        return root;
    }

    public String toString() {
        if( left == null ) {
            return value.sequence;
        }
        else if( value == null ) {
            return left.toString();
        }
        else {
            return left.toString() + value.sequence + right.toString();
        }
    }
}
