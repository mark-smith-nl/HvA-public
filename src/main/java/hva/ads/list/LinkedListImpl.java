package hva.ads.list;

import hva.ads.annotation.DefaultImplementation;
import hva.ads.annotation.FutureImplementation;

import java.util.*;

/**
 * This method <description of functionality>
 *
 * @author m.smith
 */
public class LinkedListImpl<T> implements List<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T value : this) {
            if (value == null) {
                if (o == null)
                    return true;
            } else {
                if (value.equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> currentElement = head;

            @Override
            public boolean hasNext() {
                return currentElement != null;
            }

            @Override
            public T next() {
                T value = currentElement.getValue();
                currentElement = currentElement.getNext();
                return value;
            }
        };
    }

    /* Protected for test purposes. */
    protected Iterator<T> reversedIterator() {
        return new Iterator<T>() {

            Node<T> currentElement = tail;

            @Override
            public boolean hasNext() {
                return currentElement != null;
            }

            @Override
            public T next() {
                T value = currentElement.getValue();
                currentElement = currentElement.getPrevious();
                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;
        for (T value : this) {
            array[i++] = value;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T1[]) Arrays.copyOf((T1[]) toArray(), size, a.getClass());
        System.arraycopy((T1[]) toArray(), 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        Node<T> node = new Node<>(t);
        node.previous = tail;

        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }

        tail = node;

        size++;
        return true;
    }

    @FutureImplementation
    @Override
    public boolean remove(Object o) {
        return false;
    }

    @DefaultImplementation
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @DefaultImplementation
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator<? extends T> iterator = c.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }

        return true;
    }

    @DefaultImplementation
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @DefaultImplementation
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @DefaultImplementation
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);

        int i = 0;

        for (T value : this) {
            if (i++ == index) {
                return value;
            }
        }

        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @FutureImplementation
    @Override
    public void add(int index, T element) {

    }

    @FutureImplementation
    @Override
    public T remove(int index) {
        return null;
    }

    @FutureImplementation
    @Override
    public int indexOf(Object o) {
        int index = 0;


        for (T value : this) {
            if (value == null) {
                if (o == null) {
                    return index;
                }
            } else {
                if (value.equals(o)) {
                    return index;
                }
            }
            ++index;
        }
        return -1;
    }

    @FutureImplementation
    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;

        Iterator<T> reversedIterator = reversedIterator();
        while (reversedIterator.hasNext()) {
            if (o == null) {
                if (reversedIterator.next() == null) {
                    return index;
                }
            } else {
                if (o.equals(reversedIterator.next())) {
                    return index;
                }
            }
            ++index;
        }

        return -1;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        this.clear();
        for (Object e : a) {
            this.add((T) e);
        }
    }

    @DefaultImplementation
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @DefaultImplementation
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @DefaultImplementation
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T value : this) {
            builder.append(value).append(" ");
        }
        return builder.toString();
    }

    private class Node<T> {
        private final T value;

        private Node<T> next;

        private Node<T> previous;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }
    }

    public static void main(String[] args) {
        LinkedListImpl<String> list = new LinkedListImpl<>();
        list.add("Mark");
        list.add("Tom");
        list.add("Frank");
        list.add("Petra");
        for(String value : list) {
            System.out.println(value);
        }
        System.out.println();
        Iterator<String> stringIterator = list.reversedIterator();
        while(stringIterator.hasNext()) {
            System.out.println(stringIterator.next());
        }
        System.out.println();
        System.out.println();
        Collections.sort(list);
        for(String value : list) {
            System.out.println(value);
        }
    }
}
