package hva.ads.college.week05_advanced_sorting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Sorting {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        List<Method> sortMethods = Arrays.stream(Sorting.class.getDeclaredMethods()).filter(method -> method.getName().matches("[a-z]_.*")).collect(Collectors.toList());
        sortMethods.sort(Comparator.comparing(Method::getName));

        sortMethods.forEach(m -> {
            try {
               /* System.out.printf("%sMethod %s sorting %s:%s\n\n", ANSI_RED, m.getName().substring(m.getName().lastIndexOf("_") + 1), "strings", ANSI_RESET);
                m.invoke(null, new Object[]{"mijn naam is mark smith ik woon in geldermalsen ik ben geboren in zutphen".split(" ")});
                System.out.println();*/

                /*System.out.printf("%sMethod %s sorting %s:%s\n\n", ANSI_RED, m.getName().substring(m.getName().lastIndexOf("_") + 1), "integers", ANSI_RESET);
                m.invoke(null, new Object[]{new Integer[]{5, 8, 5, 1, 99, 23, 55, 42, 45, 3, 1, 99}});
                System.out.println();*/

                System.out.printf("%sMethod %s sorting %s:%s\n\n", ANSI_RED, m.getName().substring(m.getName().lastIndexOf("_") + 1), "characters", ANSI_RESET);
                m.invoke(null, new Object[]{new Character[]{'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'}});
                System.out.println();

                /*System.out.printf("%sMethod %s sorting %s:%s\n\n", ANSI_RED, m.getName().substring(m.getName().lastIndexOf("_") + 1), "characters", ANSI_RESET);
                m.invoke(null, new Object[]{new Character[]{'Q', 'U', 'I', 'C', 'K', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'}});
                System.out.println();*/

                System.out.println("=".repeat(80));
            } catch (Exception e) {
                System.exit(1);
            }
        });
    }

    public static <T extends Comparable<T>> void a_SelectionSort(T[] values) {
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

    public static <T extends Comparable<T>> void b_InsertionSort(T[] values) {
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

    public static <T extends Comparable<T>> void c_BubbleSort(T[] values) {
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

    public static <T extends Comparable<T>> void d_MergeSort(T[] values) {
        T[] aux = Arrays.copyOf(values, values.length);
        System.out.printf("%s (Unordered)\n\n", arrayAsString(values));
        mergeSort(values, aux, 0, values.length - 1);
        System.out.printf("%s (Ordered)\n\n", arrayAsString(values));
    }

    private static <T extends Comparable<T>> void mergeSort(T[] values, T[] aux, int lo, int hi) {
        if (hi == lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(values, aux, lo, mid);
        mergeSort(values, aux, mid + 1, hi);
        merge(values, aux, lo, mid, hi);
        System.out.printf("%s [%d - %d]\n", arrayAsString(values), lo, hi);
    }

    /**
     * Rearranges source array (overwriting values).
     * Preconditions
     * - source[i] < source[i +1] ∀ i < mid (i.e. left array is sorted)
     * - source[i] < source[i +1] ∀ i > mid (i.e. right array is sorted)
     * Postcondition:
     * - source[i] < source[i +1] ∀ i < hi (i.e. complete array is sorted)
     *
     * @param values Source array
     * @param aux extra array
     * @param lo     index firstelement
     * @param mid    index last element of left array
     * @param hi     index last element
     * @param <T> Comparable class
     */
    private static <T extends Comparable<T>> void merge(T[] values, T[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = values[k];

        int i = lo, left = lo, right = mid + 1;
        while (left <= mid || right <= hi) { // While there are elements in the left arrar OR right array continue merging: i.e. overwrite value in the source array
            if (left <= mid && right <= hi) { // There are values in the left AND the right array to be processed
                int compare = aux[left].compareTo(aux[right]);
                values[i++] = compare < 0 ? aux[left++] : aux[right++];
            } else values[i++] = left > mid ? aux[right++] : aux[left++]; // There are only values in the left OR the right array to be processed
        }
    }

    public static <T extends Comparable<T>> void e_MergeSortSmallArrayOne(T[] values) {
        T[] aux = Arrays.copyOf(values, values.length);
        for (int i = 1; i < values.length; mergeSort(values, aux, 0, i++));
        System.out.printf("%s (Ordered)\n\n", arrayAsString(values));
    }

    public static <T extends Comparable<T>> void f_Quicksort(T[] values) {
        // Shuffle if needed
        System.out.printf("%s (Unordered)\n\n", arrayAsString(values));
        quickSort(values, 0, values.length - 1);
        System.out.printf("%s (Ordered)\n\n", arrayAsString(values));
    }

    private static <T extends Comparable<T>> void quickSort(T[] values, int lo, int hi) {
        if (lo == hi) return;
        int p = partition(values, lo, (hi + lo) / 2, hi);
        // The element on position p is on its final position
        if (p > lo) quickSort(values, lo, p - 1);
        if (p < hi) quickSort(values, p + 1, hi);
        System.out.printf("%s PivotIndex: %d (Unordered)\n", arrayAsString(values), p);
    }

    private static <T extends Comparable<T>> int partition(T[] values, int lo, int pivotIndex, int hi) {
        int leftIndex = lo, rightIndex = hi;

        while (leftIndex < pivotIndex || rightIndex > pivotIndex) { // While there are unchecked values either on the left or right do check.
            while (leftIndex < pivotIndex && values[leftIndex].compareTo(values[pivotIndex]) <= 0) leftIndex++;
            while (rightIndex > pivotIndex && values[rightIndex].compareTo(values[pivotIndex]) >= 0) rightIndex--;
            if (leftIndex < pivotIndex && rightIndex > pivotIndex) {
                    swap(values, leftIndex, rightIndex);
            } else if (rightIndex > pivotIndex) {
                swap(values, pivotIndex, rightIndex);
                pivotIndex = rightIndex;
            } else {
                swap(values, pivotIndex, leftIndex);
                pivotIndex = leftIndex;
            }
        }
        return pivotIndex;
    }

    private static <T extends Comparable<T>> void swap(T[] values, int indexA, int indexB) {
        T tempValue = values[indexA];
        values[indexA] = values[indexB];
        values[indexB] = tempValue;
    }

    public static <T extends Comparable<T>> String arrayAsString(T[] values) {
        return Arrays.stream(values).map(Object::toString).collect(Collectors.joining(", "));
    }

}


