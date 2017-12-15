import java.util.LinkedList;

/**
 * Created by Jan on 08.12.2017.
 *
 * TODO write evaluate method
 */

public class ExpressionTree {

    private LinkedList<ExpressionTree> children;
    private ExpressionTree parent;
    private Token token;

    public ExpressionTree( Token token ) {
        children = new LinkedList<>();
        this.token = token;
    }

    public void addChild( ExpressionTree node ) {
        children.add( node );
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public LinkedList<ExpressionTree> getChildren() {
        return children;
    }

    public Token getToken() {
        return token;
    }

    public String toString() {
        String s = "";
        s += token.sequence + ", ";
        if( !isLeaf() ) {
            s += "(";
            for( ExpressionTree n : children ) {
                s += n.toString();
            }
            s += ")";
        }
        return s;
    }
}
