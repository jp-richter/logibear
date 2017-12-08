/**
 * Created by Jan on 06.12.2017.
 */

public class Main {

    public static void main( String[] args ) {
        Tokenizer tokenizer = new Tokenizer();
        initTokenizer( tokenizer );
    }

    private static void initTokenizer( Tokenizer t ) {
        t.add("\\& | \\? | \\-> | \\<-> | \\!", 1);
        t.add("\\(", 2);
        t.add("\\)", 3);
        t.add("[a-zA-Z]", 4);
        t.add("0 | 1", 5);
    }
}
