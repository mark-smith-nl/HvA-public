package hva.ads.college.week08.functionalinterface;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {


    public static void main(String[] args) {
        List<Double> mathGrades = Arrays.asList(3.1, 6.0, 7.7, 5.3, 8.9, 5.1);
        List<Double> englishGrades = Collections.emptyList();

        ChooseOrGetDefaultValue<Double> f = (defaultValue, values) -> {
            if (values == null || values.isEmpty()) return defaultValue;
            Double maxValue = values.get(0);
            for (Double value : values) maxValue = Math.max(maxValue, value);
            return maxValue < 5.5 ? 0 : maxValue;
        };


        System.out.println(f.getValue(-1.0, mathGrades));
        System.out.println(f.getValue(-2.0, englishGrades));
        System.out.println(f.getValue(-3.0, null));
    }
}
