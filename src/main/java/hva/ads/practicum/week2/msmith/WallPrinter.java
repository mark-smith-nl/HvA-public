package hva.ads.practicum.week2.msmith;

import java.math.BigInteger;
import java.util.Scanner;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * Alternative solution to determine the possibilities to create a wall
 *
 * @author m.smithhva.nl
 */
public class WallPrinter {

    private static BigInteger totalNumberOfPossibilities;

    private static final String STANDING_BRICK = "O"; // AKA vertical brick

    private static final String COUMPOUND_BRICK = "==="; //THREE_BRICKS stacked on top of each other

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Specified length of the wall (specify a value smaller then one to end the loop): ");
            int totalNumberOfBricks = scanner.nextInt();
            if (totalNumberOfBricks < 1) System.exit(0); // Exit the loop
            totalNumberOfPossibilities = ZERO;

            BigInteger cumulativeNumberOfPossibilities = ZERO;
            int maxNumberOfCompoundBricksInWall = totalNumberOfBricks / 3; // Maximum of sets of COUMPOUND_BRICKs, three bricks stacked horizontally on top of each other
            for (int numberOfCompoundBricksInWall = 0;
                 numberOfCompoundBricksInWall <= maxNumberOfCompoundBricksInWall;
                 numberOfCompoundBricksInWall++) {
                int numberOfStandingBricksInWall = totalNumberOfBricks - 3 * numberOfCompoundBricksInWall;
                calculateIterativeNumberOfPossibilitiesWall(numberOfCompoundBricksInWall, numberOfStandingBricksInWall, ZERO);
                System.out.printf("All  possibilities with %d standing verical bricks in wall consisting of %d bricks:\n",
                        numberOfStandingBricksInWall,
                        totalNumberOfBricks);
                possibilitiesWall(numberOfCompoundBricksInWall, numberOfStandingBricksInWall, "");
                cumulativeNumberOfPossibilities = calculateIterativeNumberOfPossibilitiesWall(numberOfCompoundBricksInWall, numberOfStandingBricksInWall,
                        cumulativeNumberOfPossibilities);
            }

            System.out.printf("Aantal mogelijkheden (recursief bepaald) %d.\nAantal mogelijkheden (cummulatief recursief bepaald) %d\nAantal mogelijkheden (berekend) %d:\n%n",
                    totalNumberOfPossibilities,
                    cumulativeNumberOfPossibilities,
                    calculatedNumberOfPossibilities(totalNumberOfBricks));
        }
    }

    /**
     * Method returns all the possibilities to create a wall out of numberOfCompoundBricksInWall (seen as one large brick) and numberOfStandingBricksInWall.
     *
     * @param numberOfCompoundBricksInWall
     * @param numberOfStandingBricksInWall
     * @param result the preceding wall
     */
    private static void possibilitiesWall(int numberOfCompoundBricksInWall, int numberOfStandingBricksInWall, String result) {
        int numberOfBricks = numberOfStandingBricksInWall + numberOfCompoundBricksInWall; // Note a COMPOUNDBRICK is seen as one brick

        if (numberOfCompoundBricksInWall == numberOfBricks) { // The wall consists only of stacked bricks (sets of three)
            result += COUMPOUND_BRICK.repeat(numberOfBricks);
            totalNumberOfPossibilities = totalNumberOfPossibilities.add(ONE);
            System.out.print(result + "\n");
        } else if (numberOfStandingBricksInWall == numberOfBricks) {// The wall consists only of standing bricks
            result += STANDING_BRICK.repeat(numberOfBricks);
            totalNumberOfPossibilities = totalNumberOfPossibilities.add(ONE);
            System.out.print(result + "\n");
        } else {
            possibilitiesWall(numberOfCompoundBricksInWall, numberOfStandingBricksInWall - 1, result + STANDING_BRICK);
            possibilitiesWall(numberOfCompoundBricksInWall - 1, numberOfStandingBricksInWall, result + COUMPOUND_BRICK);
        }
    }

    // Method returns to determine the number of possibilities to create a wall .
    private static BigInteger calculateIterativeNumberOfPossibilitiesWall(int numberOfCompoundBricksInWall, int numberOfStandingBricksInWall, BigInteger totalNumberOfPossibilities) {
        int numberOfBricks = numberOfStandingBricksInWall + numberOfCompoundBricksInWall; // Note a COMPOUNDBRICK is seen as one brick

        if (numberOfCompoundBricksInWall == numberOfBricks || numberOfStandingBricksInWall == numberOfBricks)  // The wall consists of stacked (sets of three) or vertical bricks.
            return totalNumberOfPossibilities.add(ONE);

        return calculateIterativeNumberOfPossibilitiesWall(numberOfCompoundBricksInWall - 1, numberOfStandingBricksInWall,
                calculateIterativeNumberOfPossibilitiesWall(numberOfCompoundBricksInWall, numberOfStandingBricksInWall - 1, totalNumberOfPossibilities));
    }

    private static BigInteger calculatedNumberOfPossibilities(int totalNumberOfBricks) {
        BigInteger totalNumberOfPossibilities = ZERO;
        int maxNumberOfStacksOfThreeBricks = totalNumberOfBricks / 3;

        for (int numberOfStacksOfThreeBricks = 0; numberOfStacksOfThreeBricks <= maxNumberOfStacksOfThreeBricks; numberOfStacksOfThreeBricks++) {
            int numberOfStandingBricks = totalNumberOfBricks - 3 * numberOfStacksOfThreeBricks; //
            int numberOfPositions = numberOfStandingBricks + numberOfStacksOfThreeBricks;
            BigInteger numberOfPossibilities = faculty(numberOfPositions).divide(faculty(numberOfStandingBricks)).divide(faculty(numberOfStacksOfThreeBricks));
            totalNumberOfPossibilities = totalNumberOfPossibilities.add(numberOfPossibilities);
        }

        return totalNumberOfPossibilities;
    }

    private static BigInteger faculty(int n) {
        return n == 0 ? ONE : BigInteger.valueOf(n).multiply(faculty(n - 1));
    }

}

