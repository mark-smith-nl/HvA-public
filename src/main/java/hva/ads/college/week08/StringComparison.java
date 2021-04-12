package hva.ads.college.week08;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class StringComparison {

    public static int compareTo(String s1, String s2) {
        return -s1.compareTo(s2);
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Mark", "Tom", "Frank", "Petra", "Paul", "Pieter", "Irene", "Henk");
        names.forEach(System.out::println);
        System.out.println("-".repeat(40));

        names.sort(String::compareTo);
        names.forEach(System.out::println);
        System.out.println("-".repeat(40));

        /*names.sort(List::compareTo);
        names.forEach(System.out::println);
        System.out.println("-".repeat(40));*/

       // names.sort(compareTo); // Wat ontbreekt er?
        names.sort(StringComparison::compareTo); // De class reference
        names.forEach(System.out::println);
        System.out.println("-".repeat(40));

      /*  names.sort(Comparator<String>::compare); // Waarom werkt dit niet
        names.forEach(System.out::println);
        System.out.println("-".repeat(40));*/

        names.sort(String::compareTo);
        names.forEach(System.out::println);
        System.out.println("-".repeat(40));
    }
}
