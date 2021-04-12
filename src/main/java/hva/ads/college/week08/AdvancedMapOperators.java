package hva.ads.college.week08;

import java.util.HashMap;
import java.util.Map;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class AdvancedMapOperators {

    public static void main(String[] args) {
        Map<String, Integer> students = new HashMap<>();

        students.put("Mark", 4);
        students.put("Tom", 5);
        students.put("Frank", 6);
        students.put("Petra", 7);

        System.out.printf("%s -> %d\n", "Mark", students.get("Mark"));
        students.compute("Mark", (k, v) -> v * 2);
        System.out.printf("%s -> %d\n", "Mark", students.get("Mark"));
        students.merge("Mark", 3, (v1,v2) -> v1 + v2);
        System.out.printf("%s -> %d\n", "Mark", students.get("Mark"));
    }
}
