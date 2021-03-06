package hva.ads.college.week05_advanced_sorting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Sorting {

    public static void main(String[] args) {

        List<Method> sortMethods = Arrays.stream(Sorting.class.getDeclaredMethods()).filter(method -> method.getName().startsWith("sort")).collect(Collectors.toList());
        sortMethods.sort(Comparator.comparing(Method::getName));

        sortMethods.forEach(m -> {
            try {
                System.out.printf("Method %s sorting strings:\n\n", m.getName().substring(m.getName().lastIndexOf("_") + 1));
                m.invoke(null, new Object[]{"mijn naam is mark smith ik woon in geldermalsen ik ben geboren in zutphen".split(" ")});
                System.out.printf("\nMethod %s sorting integers:\n\n", m.getName());
                m.invoke(null, new Object[]{new Integer[]{5, 8, 5, 1, 99, 23, 55, 42, 45, 3}});
                System.out.println();
            } catch (Exception e) {
                System.exit(1);
            }
        });
    }

    private static <T extends Comparable<T>> void sortA_selectionSort(T[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s\n", arrayAsString(values));
            int minIndex = i;
            for (int j = i + 1; j < values.length; j++) if (values[minIndex].compareTo(values[j]) > 0) minIndex = j;
            T tempValue = values[i];
            values[i] = values[minIndex];
            values[minIndex] = tempValue;
        }

        System.out.printf("%s (Ordered)\n", arrayAsString(values));
    }

    private static <T extends Comparable<T>> void sortB_insertionSort(T[] values) {
        for (int i = 1; i < values.length; i++) {
            System.out.printf("%s\n", arrayAsString(values));

            int insertAtIndex;
            for (insertAtIndex = 0; insertAtIndex < i; insertAtIndex++) if (values[i].compareTo(values[insertAtIndex]) < 0) break;
            T tempValue = values[i];
            for (int j = i; j > insertAtIndex; values[j] = values[--j]) ; // Move values from insertAtIndex to the right (i)
            values[insertAtIndex] = tempValue;
        }

        System.out.printf("%s (Ordered)\n\n", arrayAsString(values));
    }

    private static <T extends Comparable<T>> void sortC_bubbleSort(T[] values) {
        boolean valuesSwapped;
        do {
            System.out.printf("%s\n", arrayAsString(values));
            valuesSwapped = false;
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i].compareTo(values[i + 1]) > 0) {
                    T tempValue = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = tempValue;
                    valuesSwapped = true;
                }
            }
        } while (valuesSwapped);

        System.out.printf("%s (Ordered)\n\n", arrayAsString(values));
    }

    private static <T extends Comparable<T>> void sortD_Quicksort(T[] values) {
        // Shuffle if needed
        quickSort(values, 0, values.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] values, int lo, int hi) {
        if (lo == hi) return;
        int p = quickSort(values, lo, (hi + lo) / 2, hi);

        if (p > lo) quickSort(values, lo, p - 1);
        if (p < hi) quickSort(values, p + 1, hi);
        System.out.printf("%s PivotIndex: %d (Unordered)\n", arrayAsString(values), p);

    }

    private static <T extends Comparable<T>> int quickSort(T[] values, int lo, int pivotIndex, int hi) {
        int leftIndex = lo, rightIndex = hi;

        while (leftIndex < pivotIndex || rightIndex > pivotIndex) { // While there are unchecked values either on the left or right do check.
            while (leftIndex < pivotIndex && values[leftIndex].compareTo(values[pivotIndex]) <= 0) leftIndex++;
            while (rightIndex > pivotIndex && values[rightIndex].compareTo(values[pivotIndex]) >= 0) rightIndex--;
            if (leftIndex < pivotIndex) {
                if (rightIndex > pivotIndex) {
                    T tempValue = values[leftIndex];
                    values[leftIndex] = values[rightIndex];
                    values[rightIndex] = tempValue;
                } else {
                    T tempValue = values[pivotIndex];
                    values[pivotIndex] = values[leftIndex];
                    values[leftIndex] = tempValue;
                    pivotIndex = leftIndex;
                }
            } else {
                if (rightIndex > pivotIndex) {
                    T tempValue = values[pivotIndex];
                    values[pivotIndex] = values[rightIndex];
                    values[rightIndex] = tempValue;
                    pivotIndex = rightIndex;
                }
            }
        }
        return pivotIndex;
    }


    private static <T extends Comparable<T>> String arrayAsString(T[] values) {
        return Arrays.stream(values).map(Object::toString).collect(Collectors.joining(", "));
    }
}


