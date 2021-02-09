package hva.ads.college.recursion;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class SumOfSquares {

    public static void main(String[] args) {
        System.out.println(sumOfSquaresBottomUp(5, 10));
        System.out.println(sumOfSquaresTopDown(5, 10));
        System.out.println(sumOfSquaresBiSection(5, 10));
    }

    // Top down
    public static int sumOfSquaresBottomUp(int a, int b) {
        return (a < b) ? a * a + sumOfSquaresBottomUp(a + 1, b) : a * a;
    }

    // Top down
    public static int sumOfSquaresTopDown(int a, int b) {
        return (a < b) ? b * b + sumOfSquaresTopDown(a, b - 1) : b * b;
    }

    // Top down
    public static int sumOfSquaresBiSection(int a, int b) {
        if (a == b) return a*a;

        int midden = (a+b)/2;
        return sumOfSquaresBiSection(a, midden) + sumOfSquaresBiSection(midden + 1, b);
    }
}
