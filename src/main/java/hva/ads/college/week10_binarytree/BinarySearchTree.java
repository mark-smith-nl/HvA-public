package hva.ads.college.week10_binarytree;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */

public class BinarySearchTree<T extends Comparable<T>> implements Collection<T> {

    private Node<T> root;

    @Override
    public int size() {
        return root == null ? 0 : root.size();
    }

    @Redundant("Redundant since it can be derived from the number of Nodes. Size is retrieved from the instance field.")
    public int getSize() {
        return root == null ? 0 : root.getSize();
    }

    public int depth() {
        return root == null ? 0 : root.depth();
    }

    public boolean balanced() {
        return root == null || root.balanced();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return !isEmpty() &&
                o != null &&
                root.value.getClass().isAssignableFrom(o.getClass()) &&
                root.contains((T) o);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final T[] values = root == null ? null : (T[]) toArray();

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return values != null && currentIndex < values.length;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return values[currentIndex++];
            }
        };
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return println(Node.ORDER.INORDER);
    }

    @Override
    public boolean add(T value) {
        if (value == null) return false;
        if (root == null) {
            root = new Node<T>(value);
            return true;
        }
        return root.add(value);
    }

    public boolean add(T... values) {
        return this.addAll(Arrays.asList(values));
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> values) {
        //values.stream().map(this::contains)
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> values) {
        if (values == null) return false;
        return values.stream()
                .map(this::add)
                .collect(Collectors.toSet())
                .contains(true);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }

    public T[] println(Node.ORDER order) {
        return root.toArray(order);
    }

    public int indexOf(T value) {
        return root == null ? -1 : root.indexOf(value, 0);
    }

    public T floor(T value) {
        return root == null ? null : root.floor(null, value);
    }

    public T ceiling(T value) {
        return root == null || value == null ? null : root.ceiling(null, value);
    }

    public int rank(T value) {
        return root == null || value == null ? -1 : root.rank(value);
    }

    public T get(int index) {
        return root == null || index < 0 || index >= size() ? null : root.get(index);
    }

    public Node<T> getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return String.format("BinarySearchTree size=%d(%d) depth=%d balanced=%b %s", size(), getSize(), depth(), balanced(), root);
    }

    private static class Node<T extends Comparable<T>> {

        private enum ORDER {
            INORDER,
            PREORDER,
            POSTORDER
        }

        private final T value;

        private Node<T> left;

        private Node<T> right;

        @Redundant("Redundant since it can be derived from the number of Nodes")
        private int size = 1;

        private Node(T value) {
            this.value = value;
        }

        private boolean add(T value) {
            boolean valueAdded = false;

            if (value != null) {
                int comparison = value.compareTo(this.value);
                if (comparison < 0) {
                    if (left != null) {
                        valueAdded = left.add(value);
                    } else {
                        left = new Node<T>(value);
                        valueAdded = true;
                    }
                } else if (comparison > 0) {
                    if (right != null) {
                        valueAdded = right.add(value);
                    } else {
                        right = new Node<T>(value);
                        valueAdded = true;
                    }
                }
            }

            if (valueAdded) size++;

            return valueAdded;
        }

        // The calculated size
        public int size() {
            return (left == null ? 0 : left.size()) +
                    1 +
                    (right == null ? 0 : right.size());
        }

        @Redundant("Redundant since it can be derived from the number of Nodes. Size is retrieved from the instance field.")
        public int getSize() {
            return size;
        }

        public int depth() {
            return 1 + Math.max(left == null ? 0 : left.depth(), right == null ? 0 : right.depth());
        }

        public int getCapacityForDepth(int depth) {
            return Integer.parseInt("1".repeat(depth), 2);
        }

        private boolean balanced() {
            int depth = depth();
            return depth == 1 || size() > getCapacityForDepth(depth - 1);
        }

        private boolean contains(T value) {
            if (value == null) return false;

            int comparison = value.compareTo(this.value);
            if (comparison == 0) return true;
            if (comparison < 0) return left != null && left.contains(value);
            return right != null && right.contains(value);
        }

        private int indexOf(T value, int numberOfSmallerElements) {
            if (value == null) return -1;

            int comparison = value.compareTo(this.value);
            if (comparison == 0) return numberOfSmallerElements + (left == null ? 0 : left.size());
            if (comparison < 0) {
                if (left == null) return -1;
                numberOfSmallerElements = Math.max(0, (numberOfSmallerElements));
                return left.indexOf(value, numberOfSmallerElements);
            }
            if (right == null) return -1;
            return right.indexOf(value, numberOfSmallerElements + (left == null ? 0 : left.size()) + 1);
        }

        private T[] toArray(ORDER order) {
            T[] result = (T[]) java.lang.reflect.Array.newInstance(value.getClass(), size());
            switch (order) {
                case INORDER -> {
                    toArrayInOrder(0, result);
                    return result;
                }
                case PREORDER -> {
                    toArrayPreorder(0, result);
                    return result;
                }
                case POSTORDER -> {
                    toArrayPostorder(0, result);
                    return result;
                }
            }
            return result;
        }

        private int toArrayInOrder(int i, T[] result) {
            if (left != null) i = left.toArrayInOrder(i, result);
            result[i++] = value;
            if (right != null) i = right.toArrayInOrder(i, result);
            return i;
        }

        private int toArrayPreorder(int i, T[] result) {
            result[i++] = value;
            if (left != null) i = left.toArrayPreorder(i, result);
            if (right != null) i = right.toArrayPreorder(i, result);
            return i;
        }

        private int toArrayPostorder(int i, T[] result) {
            if (left != null) i = left.toArrayPostorder(i, result);
            if (right != null) i = right.toArrayPostorder(i, result);
            result[i++] = value;
            return i;
        }

        private T floor(Node<T> previous, T value) {
            int comparison = value.compareTo(this.value);
            if (comparison == 0) return this.value;
            if (comparison < 0) return left == null ? (previous == null ? null : previous.value) : left.floor(previous, value);
            return right == null ? this.value : right.floor(this, value);
        }

        private T ceiling(Node<T> previous, T value) {
            int comparison = value.compareTo(this.value);
            if (comparison == 0) return this.value;
            if (comparison < 0) return left == null ? this.value : left.ceiling(this, value);
            return right == null ? (previous == null ? null : previous.value) : right.ceiling(previous, value);
        }

        private int rank(T value) {
            //TODO implement
            return -1;
        }

        private T get(int index) {
            int sizeLeft = left == null ? 0 : left.size;
            if (index < sizeLeft) return left.get(index);
            if (index - sizeLeft == 0) return value;
            return right.get(index - sizeLeft - 1);
        }

        @Override
        public String toString() {
            return Arrays.stream(toArray(ORDER.INORDER))
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "{", "}"));
        }
    }


    public static class TreeDiagram<T extends Comparable<T>> {

        private final int depth;

        private final Object[][] row;

        public TreeDiagram(BinarySearchTree<T> tree) {
            depth = tree.depth();

            if (depth == 0) {
                row = null;
            } else {
                row = new Object[depth][];
                for (int i = 0; i < depth; i++) {
                    row[i] = new Object[i == 0 ? 1 : 2 * row[i - 1].length];
                    if (i == 0) {
                        row[0][0] = tree.getRoot();
                    } else {
                        for (int j = 0; j < 2 * row[i - 1].length; j = j + 2) {
                            Object parent = row[i - 1][j / 2];
                            if (parent != null) {
                                row[i][j] = ((Node<?>) parent).left;
                                row[i][j + 1] = ((Node<?>) parent).right;
                            }
                        }
                    }
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();

        tree.add('S', 'E', 'A', 'C', 'R', 'H', 'M', 'X');
        System.out.println(tree);
        for (Object value : tree.toArray()) System.out.printf("Value %s has index: %d\n", value, tree.indexOf((Character) value));
        System.out.println("-".repeat(40));
        System.out.println(tree);
        Character value = 'G';
        System.out.printf("Floor('%s') = %s.\n", value, tree.floor(value));
        System.out.printf("Ceiling('%s') = %s.\n", value, tree.ceiling(value));
        value = 'Z';
        System.out.printf("Floor('%s') = %s.\n", value, tree.floor(value));
        System.out.printf("Ceiling('%s') = %s.\n", value, tree.ceiling(value));
        System.out.println("-".repeat(40));
        Node.ORDER order = Node.ORDER.INORDER;
        System.out.println(Stream.of(tree.println(order)).map(Object::toString).collect(Collectors.joining(", ", order.toString() + "[", "]")));
        order = Node.ORDER.PREORDER;
        System.out.println(Stream.of(tree.println(order)).map(Object::toString).collect(Collectors.joining(" ", order.toString() + "[", "]")));
        order = Node.ORDER.POSTORDER;
        System.out.println(Stream.of(tree.println(order)).map(Object::toString).collect(Collectors.joining(" ", order.toString() + "[", "]")));
    }

    public static void main2(String[] args) {
        BinarySearchTree<BigDecimal> tree = new BinarySearchTree<>();
        tree.add(BigDecimal.valueOf(7.0));
        tree.add(BigDecimal.valueOf(8.0));
        tree.add(BigDecimal.valueOf(7.1));
        tree.add(BigDecimal.valueOf(9.0));
        tree.add(BigDecimal.valueOf(8.6));
        tree.add(BigDecimal.valueOf(10.0));
        tree.add(BigDecimal.valueOf(8.5));
        tree.add(BigDecimal.valueOf(8.7));
        tree.add(BigDecimal.valueOf(8.65));
        System.out.println(tree);

        BigDecimal step = BigDecimal.valueOf(0.2);
        BigDecimal value = BigDecimal.valueOf(6.9);

        while (value.compareTo(BigDecimal.valueOf(10.2)) <= 0) {
            System.out.printf("Floor %.1f = %.3f\n", value, tree.floor(value));
            value = value.add(step);
        }

        System.out.println(tree);
        value = BigDecimal.valueOf(6.9);
        System.out.println("-".repeat(40));
        while (value.compareTo(BigDecimal.valueOf(10.2)) <= 0) {
            System.out.printf("Ceiling %.1f = %.3f\n", value, tree.ceiling(value));
            value = value.add(step);
        }

        System.out.println(tree);
        for (int index = 0; index < tree.size(); System.out.printf("Tree.get(%d) = %.3f\n", index, tree.get(index++))) ;

        TreeDiagram<BigDecimal> bigDecimalTreeDiagram = new TreeDiagram<>(tree);
        System.out.println();
    }

    public static void main3(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        System.out.println(tree.addAll(Arrays.asList(8, 4, 12, 2, 6, 10)));
        System.out.println(tree.addAll(Arrays.asList(8, 4, 12, 2, 6, 10, 14)));
        System.out.println(tree.addAll(Arrays.asList(8, 4, 12, 2, 6, 10, 14)));
        System.out.println(tree);
        System.out.println("-".repeat(40));
        System.out.printf("Print using %s.\n", Node.ORDER.INORDER);
        tree.forEach(System.out::println);

        System.out.println("-".repeat(40));
        System.out.printf("Print using %s.\n", Node.ORDER.PREORDER);
        for (Integer value : tree.println(Node.ORDER.PREORDER)) System.out.println(value);

        System.out.println("-".repeat(40));
        System.out.printf("Print using %s.\n", Node.ORDER.POSTORDER);
        for (Integer value : tree.println(Node.ORDER.POSTORDER)) System.out.println(value);

        System.out.println("-".repeat(40));
        System.out.println("Testing if the tree does contain values:");
        Integer[] values = (Integer[]) tree.toArray();
        for (int value : values) {
            System.out.printf("Tree (%s) contains %d: %b.\n", tree, value, tree.contains(value));
        }

        System.out.println("-".repeat(40));
        System.out.println("Testing if the tree does not contains values:");
        Object[] illegalValues = new Object[]{"Mark", LocalDate.now(), -1, 0, 1, 3, 5, 7, 9, 11, 13, 15};
        for (Object illegalValue : illegalValues) {
            System.out.printf("Tree (%s) contains %s: %b.\n", tree, illegalValue, tree.contains(illegalValue));
        }

        System.out.println("Testing if the empty tree does not contains values:");
        BinarySearchTree<Integer> emptyTree = new BinarySearchTree<>();
        for (Object illegalValue : illegalValues) {
            System.out.printf("Tree (%s) contains %s: %b.\n", emptyTree, illegalValue, emptyTree.contains(illegalValue));
        }

        System.out.println("-".repeat(40));
        System.out.println("Determine indexOf of all the components.");
        for (int value : values) System.out.printf("Tree (%s) index of %d: %d.\n", tree, value, tree.indexOf(value));

        System.out.println("-".repeat(40));
        List<Integer> v = IntStream.rangeClosed(0, 35)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(v);
        System.out.println(v.stream().map(String::valueOf).collect(Collectors.joining(", ", "Initial {", "}")));
        tree.addAll(v);
        System.out.println(tree);
        System.out.println("-".repeat(40));
        System.out.println("Determine indexOf of all the components.");
        for (int value : v) System.out.printf("Tree (%s) index of %d: %d.\n", tree, value, tree.indexOf(value));
    }
}
