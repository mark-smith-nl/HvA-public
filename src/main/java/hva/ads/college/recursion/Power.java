package hva.ads.college.recursion;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Power {

    public static void main(String[] args) {
        int base = 2;
        for (int n = 0; n < 10; n++) {
            int result = power(base, n);
            System.out.printf("%d^%d = %10d\t%20s\n", base, n, result, Integer.toString(result, base));
        }
        System.out.println();
        base = 3;
        for (int n = 0; n < 10; n++) {
            int result = power(base, n);
            System.out.printf("%d^%d = %10d\t%20s\n", base, n, result, Integer.toString(result, base));
        }
        System.out.println();
        base = 2;
        for (int n = 0; n < 10; n++) {
            int result = powerBisection(base, n);
            System.out.printf("%d^%d = %10d\t%20s\n", base, n, result, Integer.toString(result, base));
        }
    }

    public static int power(int x, int n) {
        return n == 0 ? 1 : x * power(x, n - 1);
    }

    public static int powerBisection(int x, int n) {
        if (n == 0) return 1;
        int middle = n / 2;
        int correction = n % 2 == 0 ? 1 : x;
        // X^5 = x^2 * x^2 * x
        // X^6 = x^3 * x^3 * 1
       // int a = powerBisection(x, middle);
        return powerBisection(x, middle) * powerBisection(x, middle) * correction;
     //   return a * a * correction;
    }
}
