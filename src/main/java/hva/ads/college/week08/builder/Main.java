package hva.ads.college.week08.builder;

import java.time.LocalDate;

import static java.time.LocalDate.of;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {

    private Person.PersonBuilder personBuilder;

    public static void main(String[] args) {

        Person.builder().bsn("sgsdfgsdfg").dateOfBirth(of(2000, 1, 1)).lastName("Smith").build();
        System.out.println(Person.builder()
                .bsn("1234567")
                .dateOfBirth(of(1965, 7, 28))
                .lastName("Smith")
                .firstName("Mark")
                .build());

/*
        Person p = new Person();
        Person.builder().new StepOneFinished();
        personBuilder;*/
    }
}
