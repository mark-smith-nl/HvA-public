package hva.ads.college.week02_recursion.train;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {

    public static void main(String[] args) {
        PassengerWagon firstWagon = new PassengerWagon(1, 40);
        firstWagon.addWagon(new PassengerWagon(2, 42))
                .addWagon(new PassengerWagon(3, 43))
                .addWagon(new PassengerWagon(4, 44))
                .addWagon(new PassengerWagon(5, 45));

        PassengerWagon lastWagon = firstWagon.getLastWagon();
        System.out.printf("Lastwagon: %s\n", lastWagon);
        System.out.printf("Firstwagon: %s\n", lastWagon.getFirstWagon());
        System.out.printf("Number of wagons: %d\n", firstWagon.getNumberOfWagons());

        System.out.printf("sequence: %s\n", firstWagon.sequenceAsString());
        PassengerWagon reversed = firstWagon.reverse();
        System.out.printf("Reversed sequence: %s\n", reversed.sequenceAsString());
    //    reversed.printSequence();

        //firstWagon = firstWagon.moveTailToFront();


        System.out.println(firstWagon);
    }
}
