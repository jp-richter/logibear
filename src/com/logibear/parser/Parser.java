import java.util.LinkedList;

/**
 * Created by Jan on 08.12.2017.
 *
 */

public class Parser {

    private LinkedList<Token> tokens;
    private Token lookahead;

    public Parser() {
        tokens = null;
        lookahead = null;
    }

    @SuppressWarnings("unchecked")
    public ExpressionTree parse( LinkedList<Token> toks ) throws ParserException {
        if( toks.size() == 0 ) {
            return null;
        }

        tokens = (LinkedList<Token>) toks.clone(); //safe cast
        lookahead = tokens.getFirst();

        ExpressionTree tree = expression();

        if( lookahead.token != Token.EPSILON ) {
            throw new ParserException("Unexpected symbol " +lookahead.sequence+ " found");
        }

        return tree;
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

    private ExpressionTree expression() throws ParserException {
        ExpressionTree term = term();

        if( lookahead.token == Token.IMPLICATION ) {
            ExpressionTree expression = new ExpressionTree( lookahead );
            expression.addChild( term );

            while( lookahead.token == Token.IMPLICATION ) {
                nextToken();
                expression.addChild( term() );
            }

            return expression;
        }

        return term;
    }

    private ExpressionTree term() throws ParserException {
        ExpressionTree subterm = subterm();

        if( lookahead.token == Token.OR ) {
            ExpressionTree term = new ExpressionTree( lookahead );
            term.addChild( subterm );

            while( lookahead.token == Token.OR ) {
                nextToken();
                term.addChild( subterm() );
            }

            return term;
        }

        return subterm;
    }

    private ExpressionTree subterm() throws ParserException {
        ExpressionTree argument = argument();

        if( lookahead.token == Token.AND ) {
            ExpressionTree subterm = new ExpressionTree( lookahead );
            subterm.addChild( argument );

            while( lookahead.token == Token.AND ) {
                nextToken();
                subterm.addChild( argument() );
            }

            return subterm;
        }

        return argument;
    }

    private ExpressionTree argument() throws ParserException {
        if( lookahead.token == Token.OPEN_BRACKET ) {
            nextToken();
            ExpressionTree argument = expression();

            if( lookahead.token != Token.CLOSE_BRACKET ) {
                throw new ParserException("Closing brackets expected and " +lookahead.sequence+
                "found instead");
            }
            nextToken();

            return argument;
        }


        if( lookahead.token != Token.BOOLEAN && lookahead.token != Token.VARIABLE ) {
            throw new ParserException("Boolean argument expected and " +lookahead.sequence+
                    "found instead");
        }

        ExpressionTree node = new ExpressionTree( lookahead );
        nextToken();
        return node;
    }
}
