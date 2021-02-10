package hva.ads.college.week02_recursion;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.math.BigInteger.*;

/**
 * This method calculates the result of a long division (division of two integer numbers using recursion).
 * It recognizes the repeating part in the fractional part.
 *
 * @author m.smithhva.nl
 */
public class LongDivision {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Calculate quotient");
        System.out.print("Numerator: ");
        BigInteger numerator = BigInteger.valueOf(scanner.nextInt());
        BigInteger denominator;
        boolean isZero;
        do {
            System.out.print("Denominator (not zero): ");
            denominator = BigInteger.valueOf(scanner.nextInt());
            isZero = ZERO.equals(denominator);
        } while (isZero);

        System.out.printf("Result: %s\n", divide(numerator, denominator));
    }

    private static String divide(BigInteger numerator, BigInteger denominator) {
        StringBuilder result = new StringBuilder();
        if (numerator.compareTo(ZERO) < 0 ^ denominator.compareTo(ZERO) < 0) result.append("-"); // The result is negative

        numerator = numerator.abs();
        denominator = denominator.abs();
        BigInteger[] divideAndRemainder = numerator.divideAndRemainder(denominator);
        BigInteger integerDivision = divideAndRemainder[0];
        BigInteger rest = divideAndRemainder[1];

        result.append(integerDivision.toString());
        if (!rest.equals(ZERO)) { // There is a remainder. Append the result with a decimal pint and determine the digits after the point.
            Map<BigInteger, Integer> divisionResultAtPosition = new HashMap<>();
            result.append(".");
            numerator = rest.multiply(TEN);
            StringBuilder fractionalResult = new StringBuilder();
            divide(numerator, denominator, fractionalResult, divisionResultAtPosition);
            result.append(fractionalResult);
        }

        return result.toString();
    }

    private static void divide(BigInteger numerator, BigInteger denominator, StringBuilder result, Map<BigInteger, Integer> divisionResultAtPosition) {
        if (numerator.equals(ZERO)) return;
        Integer position = divisionResultAtPosition.get(numerator);
        if (position == null) { // The division has not been encountered before
            divisionResultAtPosition.put(numerator, result.length());
            BigInteger[] bigIntegers = numerator.divideAndRemainder(denominator);
            result.append(bigIntegers[0].toString());
            divide(bigIntegers[1].multiply(TEN), denominator, result, divisionResultAtPosition);
        } else { // The division has been encountered before. The repeating fractional part can be determined.
            result.insert(position, "[").append("]R");
        }
    }
}
