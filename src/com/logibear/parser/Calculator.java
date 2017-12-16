import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Jan on 15.12.2017.
 *
 * TODO restructure evaluation and how results are stored
 * TODO handle case variables are not assigned
 * TODO check if parser results are null
 */

public class Calculator {

    private HashMap<String, Boolean> varAssignments;
    private ExpressionTree first;
    private ExpressionTree second;

    private Tokenizer tok;
    private Parser pars;

    public Calculator() {
        varAssignments = new HashMap<>();
        first = null;
        second = null;

        tok = new Tokenizer();
        initTokenizer();
        pars = new Parser();
    }

    public void assignVar( String var, boolean value ) {
        varAssignments.put( var, value );
    }

    public void clearAssignments() {
        varAssignments.clear();
    }

    public boolean evaluateExpression( String expr ) throws ParserException {
        tok.tokenize( expr );
        LinkedList<Token> l = tok.getTokens();
        first = pars.parse( l );

        ExpressionTree temp = first.clone();
        for( String key : varAssignments.keySet() ) {
            temp.assignVariable( key, varAssignments.get( key ));
        }

        return temp.evaluate();
    }

    public boolean compareExpressions( String expr1, String expr2 ) throws ParserException {
        tok.tokenize( expr2 );
        LinkedList<Token> l = tok.getTokens();
        second = pars.parse( l );

        ExpressionTree temp = second.clone();
        for( String key : varAssignments.keySet() ) {
            temp.assignVariable( key, varAssignments.get( key ));
        }

        return temp.evaluate() == evaluateExpression( expr1 );
    }

    public ExpressionTree getFirstExprTree() {
        return first;
    }

    public ExpressionTree getSecondExprTree() {
        return second;
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
}
