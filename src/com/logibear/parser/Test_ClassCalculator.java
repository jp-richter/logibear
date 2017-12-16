/**
 * Created by Jan on 08.12.2017.
 */

public class Test_ClassCalculator {

    public static void main( String[] args ) {

        System.setOut(System.err);

        Calculator calc = new Calculator();
        calc.assignVar( "A", true );
        calc.assignVar( "B", false );
        calc.assignVar( "C", true );
        calc.assignVar("D", false );
        calc.assignVar( "E", true );

        String test1 = "";
        String test2 = "A";
        String test3 = "A & B";
        String test4 = "A | B";
        String test5 = "A & B | C -> D";
        String test11 = "A -> B | C & D";
        String test12 = "A | B -> C & D";

        String test13 = "A & (B | C)";
        String test14 = "B | A & (E -> C)";

        String test6 = "A A";
        String test7 = "& &";
        String test8 = "A |";
        String test9 = "| A";
        String test10 = "|";

        String[] arr = {test1, test2, test3, test4, test5, test11, test12, test13, test14,
                test6, test7, test8, test9, test10 };

        for( String s : arr ) {
            System.out.println( "Test for string: " + s );

            try {
                boolean result = calc.evaluateExpression( s );
                System.out.println( "Result: " + result );
            }
            catch( Exception e ) {
                e.printStackTrace();
            }

            System.out.println("--------------------------------------------------");
        }
    }
}
