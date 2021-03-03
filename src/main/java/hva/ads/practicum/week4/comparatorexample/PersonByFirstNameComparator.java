package hva.ads.practicum.week4.comparatorexample;

import java.util.Comparator;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class PersonByFirstNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}
