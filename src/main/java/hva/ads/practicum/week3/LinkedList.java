package hva.ads.practicum.week3;

import java.util.Iterator;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class LinkedList<T> implements SmallList<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size;

    @Override
    public void add(T item) {
        Node<T> node = new Node<>(item);
        size++;
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            node.previous = tail;
        }
        tail = node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Out of bound");

        Node<T> node = head;
        for (int i = 0; i < index; i++) node = node.next;

        Node<T> previous = node.previous;
        Node<T> next = node.next;

        if (previous != null) previous.next = next;
        if (next != null) next.previous = previous;

        if (index == 0) head = next;
        if (index + 1 == size) tail = previous;

        size--;

        return node.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }
        };
    }

    @Override
    public String toString() {
        return listToString();
    }

    private static class Node<T> {

        private final T value;

        private Node<T> next, previous;

        private Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

}
