import java.util.LinkedList;

/**
 * Created by Jan on 08.12.2017.
 */

public class Parser {

    private LinkedList<Token> tokens;
    private Token lookahead;
    private ExpressionNode tree;

    public Parser() {
        tree = null;
    }

    @SuppressWarnings("unchecked")
    public void parse( LinkedList<Token> tokens ) throws ParserException {
        if( tokens.size() == 0 ) {
            return;
        }

        this.tokens = (LinkedList<Token>) tokens.clone(); //safe cast
        lookahead = this.tokens.getFirst();

        tree = new ExpressionNode();

        expression();

        if( lookahead.token != Token.EPSILON ) {
            throw new ParserException("Unexpected symbol " +lookahead.sequence+ " found");
        }
    }

    public ExpressionNode getExpressionTree() throws ParserException {
        if( tree != null ) {
            return tree.getRoot();
        }
        else {
            throw new ParserException("No expression tree was generated");
        }
    }

    private void nextToken() {
        tokens.pop();
        if( tokens.isEmpty() ) {
            lookahead = new Token(Token.EPSILON, "");
        }
        else {
            lookahead = tokens.getFirst();
        }
    }

    private void expression() throws ParserException{
        term();
    }

    private void term() throws ParserException {
        factor();
        operator();
    }

    private void factor() throws ParserException {
        //if token == !, read next token and put !var/bool in tree
        if( lookahead.token == Token.OPEN_BRACKET ) {
            nextToken();
            expression();

            if( lookahead.token != Token.CLOSE_BRACKET ) {
                throw new ParserException("Closing brackets expected and " +lookahead.sequence+
                "found instead");
            }
        }

        tree.add( lookahead );
        nextToken();
    }

    private void operator() throws ParserException {
        tree.add( lookahead );
        nextToken();
        if( lookahead.token == Token.AND_OR) {
            term();
        }
        else if( lookahead.token == Token.IMPLICATION ) {
            expression();
        }
        else {
            //lookahead == EPSILON
        }
    }
}
