package hva.ads.practicum.week4.comparatorexample;

import java.util.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {

    public static void main(String[] args) {
        for (String arg: args) System.out.println(arg);

        List<Person> persons = new ArrayList<>();

        persons.add(new Person(55, "Mark", "Smit"));
        persons.add(new Person(19, "Tom", "Smith"));
        persons.add(new Person(16, "Frank", "Smidt"));
        persons.add(new Person(50, "Petra", "Eingenaldus"));

        Collections.sort(persons);
        for (Person p : persons) System.out.println(p);

        System.out.println();
        persons.sort(new PersonByFirstNameComparator());
       // Collections.sort(persons, new PersoonOpVoornaamComparator());
        for (Person p : persons) System.out.println(p);

        System.out.println();
        persons.sort(Comparator.comparing(Person::getLastName));
        for (Person p : persons) System.out.println(p);
    }
}
