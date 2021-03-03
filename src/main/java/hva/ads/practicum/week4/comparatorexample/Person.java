package hva.ads.practicum.week4.comparatorexample;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Person implements Comparable<Person> {

    private final int age;

    private final String firstName;

    private final String lastName;

    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return age - otherPerson.age;
    }

    @Override
    public String toString() {
        return format("Person{age= %d, firstName='%s' lastName='%s'}", age, firstName, lastName);
    }
}
