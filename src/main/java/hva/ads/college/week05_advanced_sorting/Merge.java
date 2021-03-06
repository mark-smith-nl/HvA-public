package hva.ads.college.week05_advanced_sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Merge {
    public static void main2(String[] args) {
        for (int run = 0; run < 10; run++) {
            Random rd = new Random();

            Integer[] values = new Integer[20];
            for (int i = 0; i < values.length; i++) values[i] = rd.nextInt(100);
            int mid = rd.nextInt(values.length);
            System.out.println(mid);

            Integer[] left = new Integer[mid + 1];
            for (int i = 0; i <= mid; i++) left[i] = values[i];

            Integer[] right = new Integer[values.length - mid - 1];
            for (int i = 0; i < right.length; i++) right[i] = values[mid + 1 + i];

            Sorting.a_SelectionSort(left);
            Sorting.a_SelectionSort(right);

            Integer[] valuesMerge = new Integer[values.length];
            for (int i = 0; i <= mid; i++) valuesMerge[i] = left[i];
            for (int i = 0; i < right.length; i++) valuesMerge[i + mid + 1] = right[i];
            merge2(valuesMerge, new Integer[valuesMerge.length], 0, mid, valuesMerge.length - 1);

            for (Integer integer : valuesMerge) System.out.printf("%d  ", integer);
            for (int i = 1; i < valuesMerge.length; i++) if (valuesMerge[i - 1].compareTo(valuesMerge[i]) > 0) throw new IllegalStateException("Not sorted");
            System.out.println("\nOK\n");
        }
    }

    private static <T extends Comparable<T>> void merge2(T[] a, T[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] values = new Integer[20];
        for (int i = 0; i < values.length; i++) values[i] = random.nextInt(100);
        sort(values);
        for (Integer integer : values) System.out.printf("%d  ", integer);
    }
    public static <T extends Comparable<T>> void sort(T[] values) {
        T[] aux = Arrays.copyOf(values, values.length);
        sort(values, aux, 0, values.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] values, T[] aux, int lo, int hi) {
        if (hi == lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(values, aux, lo, mid);
        sort(values, aux, mid + 1, hi);
        merge(values, aux, lo, mid, hi);
    }

    /**
     * Rearranges source array (overwriting values).
     * Preconditions
     * - source[i] < source[i +1] ∀ i < mid (i.e. left array is sorted)
     * - source[i] < source[i +1] ∀ i > mid (i.e. right array is sorted)
     * Postcondition:
     * - source[i] < source[i +1] ∀ i < hi (i.e. complete array is sorted)
     * @param values Source array
     * @param aux
     * @param lo     index firstelement
     * @param mid    index last element of left array
     * @param hi     index last element
     * @param <T>
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
}
