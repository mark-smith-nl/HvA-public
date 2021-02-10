package hva.ads.college.week02_recursion;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class GreatestCommonDenominator {

    public static void main(String[] args) {
        System.out.println(getGcd(17, 15));
        System.out.println(getGcd(35, 17));
        System.out.println(getGcd(64, 16));
    }

    public static int getGcd(int a, int b) {
        if (b == 0) return a;
        return getGcd(b, a % b);
    }
}
