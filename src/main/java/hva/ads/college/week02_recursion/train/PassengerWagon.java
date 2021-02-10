package hva.ads.college.week02_recursion.train;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class PassengerWagon extends Wagon<PassengerWagon> {

    private final int numberOfSeats;

    public PassengerWagon(int id, int numberOfSeats) {
        super(id);
        this.numberOfSeats = numberOfSeats;
    }

    public int getTotalNumberOfseats() {
        // TODO implementation
        return 0;
    }

    @Override
    public String toString() {
        return format("%s numberOfSeats=%d", super.toString(), numberOfSeats);
    }
}
