package hva.ads.practicum.week11;

import java.util.Objects;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Vertex<T extends Comparable<T>> implements Identifiable<T>{

    private final T id;

    private final Object value;

    @Override
    public T getId() {
        return id;
    }

    public Vertex(T id) {
        this(id , null);
    }

    public Vertex(T id, Object value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return id.equals(vertex.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
