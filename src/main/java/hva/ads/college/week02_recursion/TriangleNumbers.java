package hva.ads.college.week02_recursion;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class TriangleNumbers {

    public static void main(String[] args) {
        for (int i = 1; i < 7; i++) {
            System.out.printf("Total number of elements in triangular with base %d: \t%d\n", i, triangular(i));
        }
    }

    public static int triangular(int base) {
        return base == 1 ? 1 : base + triangular(--base);
    }

    public int triangle(int n) {
        return n*(n+1)/2;
    }

}
