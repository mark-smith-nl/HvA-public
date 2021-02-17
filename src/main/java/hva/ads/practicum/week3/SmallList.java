package hva.ads.practicum.week3;

import java.util.Iterator;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public interface SmallList<T> extends Iterable<T> {

    void add(T item);

    default void add(T... item) {
        for (int i = 0; i < item.length; add(item[i++])) ;

       /* Verbose
        for (int i = 0; i < item.length; i++) {
            add(item[i]);
        };*/
    }

    int size();

    default T get(int index) {
        if (index >= size()) throw new IllegalArgumentException("Out of bound");

        Iterator<T> iterator = iterator();
        for (int i = 0; i < index; i++) iterator.next(); // Loop through the values. Do not assign the value to a variable.

        return iterator.next();
    }

    T remove(int index);

    default public int find(T item) {
        Iterator<T> iterator = iterator();
        int i = -1;
        while (iterator.hasNext()) {
            i++;
            if (item.equals(iterator.next())) return i;
        }
        return -1;
    }

    // Note: We can not name this method toString() since the toString method is already defined in Object causing the 'deadly diamond of death'.
    default String listToString() {
        StringBuilder toString = new StringBuilder();

        for (T t : this) toString.append(t).append("-->");

        return toString.append("NULL").toString();
    }
}
