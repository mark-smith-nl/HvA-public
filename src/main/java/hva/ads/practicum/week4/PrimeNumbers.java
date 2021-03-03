package hva.ads.practicum.week4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class PrimeNumbers {

    public static void main(String[] args) {
        int upperBound = 2;

        List<Method> methods = Arrays.stream(PrimeNumbers.class.getDeclaredMethods())
                .filter(method -> method.getName().startsWith("getNumberOfPrimeNumbers"))
                .collect(Collectors.toList());

        Map<String, Map<Integer, BigInteger>> results = new TreeMap<>();
        methods.forEach(method -> results.put(method.getName(), new TreeMap<>()));

        do {
            int a = upperBound;

            methods.forEach(method -> {
                try {
                    long start = System.nanoTime();
                    int numberOfPrimeNumbers = (Integer) method.invoke(null, a);
                    long finish = System.nanoTime();
                    results.get(method.getName()).put(a, BigInteger.valueOf(finish - start));
                    System.out.printf("Algorithm: %s. Number of primenumbers [2 - %8d]: %5d. Time elapsed %10d\n", method.getName(), a, numberOfPrimeNumbers, finish - start);
                } catch (Exception e) {
                    System.exit(-1);
                }
            });
            System.out.println();
            upperBound *= 2;
        } while (upperBound < 100000);

        System.out.println();
        results.forEach((name, integerBigIntegerMap) -> {
            System.out.println(name);
            System.out.println("-".repeat(35));
            System.out.printf("| %14s | %14s |\n", "log(t)", "log(N)");
            integerBigIntegerMap.forEach((N, t) -> System.out.printf("| %14.8f | %14.8f |\n", Math.log(t.longValue()), Math.log(N)));
            System.out.println("-".repeat(35) + "\n");
        });
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
