package hva.ads.college.week08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CompositionOfFunctions {

    // One predicate
    private static void showFilteredIntegersUsingOnePredicate(List<Integer> integers, Predicate<Integer> predicate) {
        integers.forEach(i -> {
            if (predicate.test(i)) System.out.printf("%d ", i);
        });
        System.out.println();
    }

    // Two predicates
    private static void showFilteredIntegersUsingTwoPredicates(List<Integer> integers, Predicate<Integer> p1, Predicate<Integer> p2) {
        integers.forEach(i -> {
            if (p1.and(p2).test(i)) System.out.printf("%d ", i);
        });
        System.out.println();
    }

    // Multiple predicates
    private static void showFilteredIntegersUsingMultiplePredicates(List<Integer> integers, Predicate<Integer>... predicates) {
        Predicate<Integer> predicate = integer -> true;
        for (Predicate<Integer> p :predicates) predicate = predicate.and(p);
        showFilteredIntegersUsingOnePredicate(integers, predicate);
    }

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList(Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,19, 20, 21, 22, 23, 24));

        // Show positive integers
   //     showFilteredIntegersUsingOnePredicate(integers, i -> i > 0);

        // Show positive even integers
   //     showFilteredIntegersUsingTwoPredicates(integers, i -> i > 0, i -> i % 2 == 0);

        // Show positive even integers, multiple of three
        showFilteredIntegersUsingMultiplePredicates(integers, i -> i > 0, i -> i % 2 == 0, i -> i % 3 == 0);
    }
}
