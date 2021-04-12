package hva.ads.college.week08;

import java.util.function.UnaryOperator;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class LambdaExampleUnaryOperator {

    public static void main(String[] args) {
        UnaryOperator<Double> unaryOperator = x -> Math.pow(x, 2) + 1;
        System.out.println(unaryOperator.apply(3.0));
    }
}
