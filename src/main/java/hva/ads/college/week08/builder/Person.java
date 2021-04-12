package hva.ads.college.week08.builder;

import java.time.LocalDate;

/**
 * <b>Immutable</b> class for storing person data.
 *
 * @author m.smithhva.nl
 */
public class Person {

    private String bsn; // Required

    private LocalDate dateOfBirth; // Required

    private String lastName; // Required

    private String firstName; // Optional

    private short weight; // Optional

    private Person() {} // Notice private modifier

    public String getBsn() {
        return bsn;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public short getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "bsn='" + bsn + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", weight=" + weight +
                '}';
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder extends Builder<Person> {  // Notice public modifier
        private PersonBuilder() { // Notice private modifier
            super(new Person());
        }

        public StepOneFinished bsn(String bsn) {
            if (bsn == null || bsn.isBlank()) throw new IllegalArgumentException();
            instance.bsn = bsn.trim();
            return this.new StepOneFinished();
        }

        // Public inner class associated with a PersonBuilder instance
        public class StepOneFinished {
            private StepOneFinished() {} // Notice private modifier

            public StepTwoFinished dateOfBirth(LocalDate dateOfBirth) {
                if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) throw new IllegalArgumentException();
                instance.dateOfBirth = dateOfBirth;
                return PersonBuilder.this.new StepTwoFinished();
            }
        }

        // Public inner class associated with a PersonBuilder instance
        public class StepTwoFinished {
            private StepTwoFinished() {} // Notice private modifier

            public FinalStep lastName(String lastName) {
                if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException();
                instance.lastName = lastName.trim();
                return PersonBuilder.this.new FinalStep();
            }
        }

        // Public inner class associated with a PersonBuilder instance
        // Final step facilitates the return of the instance in the build() method.
        public class FinalStep {
            private FinalStep() {} // Notice private modifier

            public FinalStep firstName(String firstName) {
                instance.firstName = firstName;
                return this;
            }

            public void weight(short weight) {
                instance.weight = weight;
            }

            public Person build() {
                return instance;
            }
        }
    }

}
