package development;

import java.math.BigInteger;

import static java.lang.System.out;
import static java.math.BigInteger.*;
import static java.math.BigInteger.valueOf;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {

    public static void main(String[] args) {
        BinaryOperation<BigInteger>  bigIntegerSumOperation = numbers -> {
            BigInteger sum = ZERO;
            for (BigInteger number : numbers) sum = sum.add(number);
            return sum;
        };

        out.println(bigIntegerSumOperation.execute(valueOf(1), valueOf(2), valueOf(3), valueOf(4), valueOf(5), valueOf(6), valueOf(7), valueOf(8), valueOf(9)));
    }
}
