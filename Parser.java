import java.util.LinkedList;

/**
 * Created by Jan on 08.12.2017.
 */

public class Parser {

    LinkedList<Token> tokens;
    Token lookahead;

    public void parse( LinkedList<Token> tokens ) {
        this.tokens = (LinkedList<Token>) tokens.clone();
        lookahead = this.tokens.getFirst();

        expression();

        if( lookahead.token != Token.EPSILON ) {
            throw new ParserException("Unexpected symbol " +lookahead+ " found");
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

    private void expression() {

    }

    private void term() {

    }

    private void factor() {

    }

    private void operator() {

    }

    private void value() {

    }
}
