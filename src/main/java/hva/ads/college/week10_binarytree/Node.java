package hva.ads.college.week10_binarytree;

import java.util.Scanner;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Node<T extends Comparable<T>> {

    private final T value;

    private Node<T> left;

    private Node<T> right;

    public Node(T value) {
        this.value = value;
    }

    public Node<T> add(T value) {
        if (value == null) return this;

        int comparison = value.compareTo(this.value);

        if (comparison == 0) return this;

        if (comparison < 0) {
            if (left != null) return left.add(value);
            left = new Node<>(value);
            return this;
        }

        if (right != null) return right.add(value);
        right = new Node<>(value);
        return this;
    }

    public int getNumberOfElements() {
        return (left == null ? 0 : left.getNumberOfElements()) +
                1 +
                (right == null ? 0 : right.getNumberOfElements());
    }

    public int getDepth() {
        return 1 + Math.max(left == null ? 0 : left.getDepth(), right == null ? 0 : right.getDepth());
    }

    public int getCapacityForDepth(int depth) {
        return Integer.parseInt("1".repeat(depth), 2);
    }

    public boolean isBalanced() {
        int depth = getDepth();
        return depth == 1 || getNumberOfElements() > getCapacityForDepth(depth - 1);
    }

    public T[] toArray() {
        int numberOfElements = getNumberOfElements();
        T[] result = (T[])java.lang.reflect.Array.newInstance(value.getClass(), numberOfElements);
        toArray(0, result);
        return result;
    }

    public int toArray(int i, T[] result) {
        if (left != null) i = left.toArray(i, result);
        result[i++] = value;
        if (right != null) i = right.toArray(i, result);
        return i;
    }

    public String getValuesAsString() {
        StringBuilder toString = new StringBuilder();
        if (left != null) toString.append(left.getValuesAsString()).append("-");
        toString.append(value);
        if (right != null) toString.append("-").append(right.getValuesAsString());

        return toString.toString();
    }

    @Override
    public String toString() {
        return String.format("%s. Number of elements: %d. Depth: %d. Balanced: %b. Capacity: %d",
                getValuesAsString(),
                getNumberOfElements(),
                getDepth(),
                isBalanced(),
                getCapacityForDepth(getDepth()));
    }

    public static void main(String[] args) {
        Node<Integer> tree = null;
        Scanner scanner = new Scanner(System.in);
        int sentinel = 0;
        int value;
        do {
            System.out.printf("Input integer number. Please specify %d to signal the end of input: ", sentinel);
            value = scanner.nextInt();
            if (value != sentinel) {
                if (tree == null) tree = new Node<>(value);
                else tree.add(value);
                System.out.println(tree);
            }
        } while (value != sentinel);


        Integer[] integers = tree.toArray();
        System.out.println();
    }
}
