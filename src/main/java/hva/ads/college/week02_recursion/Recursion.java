package hva.ads.college.week02_recursion;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Recursion {

    public static void main(String[] args) {
        //    printerIteratief(0, 19000);
    //    printer(0, 19000); // Variable maximum value: 19000 always results in a StackOverflowError
        printer(0, 10); // Variable maximum value: 19000 always results in a StackOverflowError
    }

    // Recursive solution
    public static void printer(int count, int max) {
        if (count < max) { // Eindconditie

            System.out.println(count);
            printer(count + 1, max);
            System.out.printf("printer %d finishing\n", count);
        }
    }

    // Iterative solution
    public static void printerIteratief(int count, int max) {
        for (int i = count; i < max; System.out.println(i++)) ;
    }
}
