package hva.ads.practicum.week3;

import java.util.Iterator;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class SmallArrayList<T> implements SmallList<T> {

    private static final int INITIAL_CAPACITY = 10;

    private int capacity;

    private int size;

    private T[] values;

    public SmallArrayList() {
        this(INITIAL_CAPACITY);
    }

    public SmallArrayList(int capacity) {
        this.capacity = capacity;
        values = (T[]) new Object[capacity];
    }

    @Override
    public void add(T item) {
        if (size == capacity) increaseCapacity();

        values[size++] = item;
    }

    private void increaseCapacity() {
        System.out.println("Increase capacity...");
        capacity *= 2;
        T[] newValues = (T[]) new Object[capacity];
        for (int i = 0; i < values.length; newValues[i] = values[i++]) ;
        values = newValues;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Out of bound");
        T value = values[index];
        for (int i = index; i < size - 1; values[i] = values[i++ + 1]) ;
        values[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return values[index++];
            }
        };
    }

    @Override
    public String toString() {
        return listToString();
    }

}

