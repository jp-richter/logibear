/**
 * Created by Jan on 08.12.2017.
 */

public class Token {

    public static final int EPSILON = 0;
    public static final int BOOLEAN = 1;
    public static final int VARIABLE = 2;
    public static final int AND_OR = 3;
    public static final int IMPLICATION = 4;
    public static final int OPEN_BRACKET = 5;
    public static final int CLOSE_BRACKET = 6;

    public final int token;
    public final String sequence;

    public Token(int token, String sequence) {
        this.token = token;
        this.sequence = sequence;
    }
}
