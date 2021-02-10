package hva.ads.practicum.week2;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Fibonacci {

    public static void main(String[] args) {
        for (int i = 0; i < 10; System.out.print(" " + fibonacci(i++))) ;
    }

    /**
     * Stop
     * - condition fibonacci(0) = 0
     * - condition fibonacci(1) = 1
     *
     * @param i argument voor de Fibonacci functie
     * @return resuktaat Fibonacci
     */
    private static int fibonacci(int i) {
        return i < 2 ? i : fibonacci(i - 1) + fibonacci(i - 2);
    }
}
