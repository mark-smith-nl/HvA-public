package hva.ads.college.week10_binarytree;

import com.sun.source.tree.Tree;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.TreeSet;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
class TreeSetExample {

    public static void main(String[] args) {
        // Set<Person> persons = new TreeSet<>(); Construction of sorted collection with non-comparable elements
        System.out.println("Tree uses specified sorting in the NON-Comparable class (obligated)");
        Set<Person> persons = new TreeSet<>((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
        persons.add(new Person("Mark", "Smith", 55));
        persons.add(new Person("Tom", "Smith", 19));
        persons.add(new Person("Frank", "Smith", 16));
        persons.add(new Person("Petra", "Ringenaldus", 50));
        persons.add(new Person("Irene", "Klaasen", 80));
        persons.add(new Person("Henk", "Dekker", 83));
        persons.add(new Person("Lex", "Oranje", 54));
        persons.add(new Person("Beatrix", "Oranje", 83));
        persons.add(new Person("Paul", "Young", 65));
        persons.forEach(System.out::println);
        System.out.println("-".repeat(40));

        System.out.println("Tree uses specified sorting in the NON-Comparable class (obligated)");
        Set<Person> personsTwo = new TreeSet<>((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));
        personsTwo.addAll(persons);
        personsTwo.forEach(System.out::println);
        System.out.println("-".repeat(40));

        System.out.println("Tree uses specified sorting in the Comparable class (NOT obligated)");
        Set<SortablePerson> sortablePersons = new TreeSet<>((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
        sortablePersons.add(new SortablePerson("Mark", "Smith", 55));
        sortablePersons.add(new SortablePerson("Tom", "Smith", 19));
        sortablePersons.add(new SortablePerson("Frank", "Smith", 16));
        sortablePersons.add(new SortablePerson("Petra", "Ringenaldus", 50));
        sortablePersons.add(new SortablePerson("Irene", "Klaasen", 80));
        sortablePersons.add(new SortablePerson("Henk", "Dekker", 83));
        sortablePersons.add(new SortablePerson("Lex", "Oranje", 54));
        sortablePersons.add(new SortablePerson("Beatrix", "Oranje", 83));
        sortablePersons.add(new SortablePerson("Paul", "Young", 65));
        sortablePersons.forEach(System.out::println);
        System.out.println("-".repeat(40));

        System.out.println("Tree uses default sorting in the Comparable class");
        Set<SortablePerson> sortablePersonsTwo = new TreeSet<>();
        sortablePersons.add(new SortablePerson("Mark", "Smith", 55));
        sortablePersons.add(new SortablePerson("Tom", "Smith", 19));
        sortablePersons.add(new SortablePerson("Frank", "Smith", 16));
        sortablePersons.add(new SortablePerson("Petra", "Ringenaldus", 50));
        sortablePersons.add(new SortablePerson("Irene", "Klaasen", 80));
        sortablePersons.add(new SortablePerson("Henk", "Dekker", 83));
        sortablePersons.add(new SortablePerson("Lex", "Oranje", 54));
        sortablePersons.add(new SortablePerson("Beatrix", "Oranje", 83));
        sortablePersons.add(new SortablePerson("Paul", "Young", 65));
        sortablePersons.forEach(System.out::println);
        System.out.println("-".repeat(40));
    }

}

class Person {

    private final String firstName;

    private final String lastName;

    private final int age;

    public Person(@NotNull String firstName, @NotNull String lastName, @NotNull int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

class SortablePerson extends Person implements Comparable<SortablePerson> {

    public SortablePerson(@NotNull String firstName, @NotNull String lastName, @NotNull int age) {
        super(firstName, lastName, age);
    }

    @Override
    public int compareTo(SortablePerson o) {
        return getAge() - o.getAge();
    }
}
