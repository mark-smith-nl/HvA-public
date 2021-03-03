package hva.ads.practicum.week4;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class PrimeNumbers {

    public static void main(String[] args) {
        int upperBound = 100;

        do {

            long start;
            int numberOfPrimeNumbers;
            long finish;

            start = System.nanoTime();
            numberOfPrimeNumbers = getNumberOfPrimeNumbersA1(upperBound);
            finish = System.nanoTime();
            System.out.printf("Number of primenumbers [2-%d]: %s. Time elapsed %10d\n", upperBound, numberOfPrimeNumbers, finish - start);

            start = System.nanoTime();
            numberOfPrimeNumbers = getNumberOfPrimeNumbersA2(upperBound);
            finish = System.nanoTime();
            System.out.printf("Number of primenumbers [2-%d]: %s. Time elapsed %10d\n", upperBound, numberOfPrimeNumbers, finish - start);

            start = System.nanoTime();
            numberOfPrimeNumbers = getNumberOfPrimeNumbersA3(upperBound);
            finish = System.nanoTime();
            System.out.printf("Number of primenumbers [2-%d]: %s. Time elapsed %10d\n", upperBound, numberOfPrimeNumbers, finish - start);

            start = System.nanoTime();
            numberOfPrimeNumbers = getNumberOfPrimeNumbersA4(upperBound);
            finish = System.nanoTime();
            System.out.printf("Number of primenumbers [2-%d]: %s. Time elapsed %10d\n", upperBound, numberOfPrimeNumbers, finish - start);
            System.out.println();
            upperBound *= 2;
        } while (upperBound < 10000000);
    }

    public static int getNumberOfPrimeNumbersA1(int upperBound) {
        int p = 2;
        int numberOfPrimes = 0;
        while (p <= upperBound) {
            int divisor = 2;
            boolean isPossiblePrime = true;
            while (divisor < p && isPossiblePrime) {
                isPossiblePrime = p % divisor != 0;
                divisor++;
            }
            if (isPossiblePrime) numberOfPrimes++;
            p++;
        }

        return numberOfPrimes;
    }

    public static int getNumberOfPrimeNumbersA2(int upperBound) {

        int p = 3;
        int numberOfPrimes = 1;
        while (p <= upperBound) {
            int divisor = 2;
            boolean isPossiblePrime = true;
            while (divisor <= (int) Math.sqrt(p) && isPossiblePrime) {
                isPossiblePrime = p % divisor != 0;
                divisor++;
            }
            if (isPossiblePrime) numberOfPrimes++;
            p += 2;
        }

        return numberOfPrimes;
    }

    public static int getNumberOfPrimeNumbersA3(int upperBound) {

        int p = 3;
        int numberOfPrimes = 1;
        while (p <= upperBound) {
            int divisor = 2;
            boolean isPossiblePrime = true;
            while (divisor * divisor <= p && isPossiblePrime) {
                isPossiblePrime = p % divisor != 0;
                divisor++;
            }
            if (isPossiblePrime) numberOfPrimes++;
            p += 2;
        }

        return numberOfPrimes;
    }

    public static int getNumberOfPrimeNumbersA4(int upperBound) {
        int[] p = new int[upperBound - 1];
        for (int i = 0; i < upperBound - 1; p[i++] = i + 1) ; // Fill the array with values p[0] = 2, p[1] = 3 .... p[ubound - 2] = ubound
        int currentIndex = 0;

        do {
            for (int i = currentIndex + p[currentIndex]; i < p.length; i += p[currentIndex]) p[i] = -1; // Set values to -1 at equal distances: p[currentIndex]
            currentIndex++;
            while (currentIndex < p.length && p[currentIndex] == -1) currentIndex++; // get the index of the next prime (or an index == p.length)
        } while (currentIndex < p.length);

        int numberOfPrimes = 0;
        for (int x : p) if (x != -1) numberOfPrimes++;

        return numberOfPrimes;
    }
}
