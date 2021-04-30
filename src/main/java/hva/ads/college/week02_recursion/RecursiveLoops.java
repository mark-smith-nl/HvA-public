package hva.ads.college.week02_recursion;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class RecursiveLoops {

    public static void main(String[] args) {

        loop(0,
                value -> value.compareTo(10) < 0,
                value -> value + 1,
                System.out::println);

        loop(0,
                value -> value.compareTo(40) < 0,
                value -> value + 1,
                value -> System.out.print("-"),
                "",
                "\nExample\n");

        loop(10,
                value -> value.compareTo(0) > 0,
                value -> value - 2,
                System.out::println);
    }

    private static void loop(int i, Predicate<Integer> endCondition, UnaryOperator<Integer> unaryOperator, Consumer<Integer> body) {
        if (endCondition.test(i)) {
            body.accept(i);
            loop(unaryOperator.apply(i), endCondition, unaryOperator, body);
        }
    }

    private static void loop(int i, Predicate<Integer> endCondition, UnaryOperator<Integer> unaryOperator, Consumer<Integer> body, String messageInitialize,
                             String messageTerminate) {
        System.out.print(messageInitialize);
        loop(i, endCondition, unaryOperator, body);
        System.out.print(messageTerminate);
    }
}
