package hva.ads.practicum.week2.kmensah;

import java.util.Scanner;

/**
 * @author Kenneth Mensah - ISR202a
 */
public class WallPrinter {

    private static int count;   // het aantal combinaties wordt bij gehouden met een final static int. Hierdoor kun je deze
    // aanroepen in de methode.

    private static final String HORIZONTAL_BRICK = "==="; // final string voor de horizontale stenen (static voor aanroepen)
    private static final String VERTICAL_BRICK= "0"; // final string voor de verticale stenen (static voor aanroepen)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // scanner voor het inlezen van een waarde.
        String wall = "";

        System.out.println("Welcome to the Wall Designer.");
        System.out.println("We will be using 3x1 bricks. \n");
        System.out.print("How long do you want your walls to be? ");


        // scanner.nextInt wordt gebruikt om een int getal in te laden voor beide methodes.

        generateWall(wall, scanner.nextInt());
//        generateWallCombinations(scanner.nextInt());
        System.out.println(count + " possible wall(s)");
    }

    /**
     * Deze methode genereert een muur en print deze uit.
     *
     *         buildWall(done, size): void
     *         begin
     *             if size < 0 then return
     *             if size = 0 then
     *                 print done
     *             else
     *                 buildWall(done + “|”,size-1)
     *                 buildWall(done + “==“,size-2)
     *             endif
     *         end
     * @param wall de muur die geprint wordt
     * @param length de lengte van de muur
     */
    static void generateWall(String wall, int length) {

        if (length < 0){
        }
        else if (length == 0){
            System.out.println(wall);
            count++;
        }
        else {
            generateWall(wall + HORIZONTAL_BRICK,length - 3); // === heeft een length van 3
            generateWall(wall + VERTICAL_BRICK,length - 1); // heeft een length van 1
        }
    }

    /**
     * @param length de lengte van de muur
     */
    static void generateWallCombinations(int length) {

        if (length < 0){
        }
        else if (length == 0){
            count++;
        }
        else {
            generateWallCombinations(length - 3); // === heeft een length van 3
            generateWallCombinations(length - 1); // heeft een length van 1
        }
    }
}
