package hva.ads.college.week02_recursion.train;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class Wagon<T extends Wagon<T>> {

    protected T next;

    protected T previous;

    private final int id;

    public Wagon(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfWagons() {
        return 1 + (next == null ? 0 : next.getNumberOfWagons());
    }

    @SuppressWarnings("unchecked cast")
    public T getLastWagon() {
        return next == null ? (T) this : next.getLastWagon();
    }

    @SuppressWarnings("unchecked cast")
    public T getFirstWagon() {
        return previous == null ? (T) this : previous.getFirstWagon();
    }

    @SuppressWarnings("unchecked cast")
    public T moveTailToFront() {
        T firstWagon = getFirstWagon();
        T lastWagon = getLastWagon();

        if (firstWagon != lastWagon) {
            T previousTolast = lastWagon.previous;
            // Disconnect last
            previousTolast.next = null;
            lastWagon.previous = null;

            // Put last in front
            firstWagon.previous = lastWagon;
            lastWagon.next = firstWagon;

        }
        return lastWagon;
    }

    @SuppressWarnings("unchecked cast")
    public T addWagon(T wagon) {
        next = wagon;
        wagon.previous = (T) this;
        return wagon;
    }

    public T reverse() {
        T lastWagon = getLastWagon();
        if (this == lastWagon) return (T) this;

        T previousTolast = lastWagon.previous;
        // Disconnect last. Always 2 operations
        previousTolast.next = null;
        lastWagon.previous = null;

        T secondWagon = reverse();

        // Connect the last wagon (the new first) to the reversed rest sequence. Always 2 operations
        lastWagon.next = secondWagon;
        secondWagon.previous = lastWagon;

        return lastWagon;
    }

    public String sequenceAsString() {
        return toString() + (next == null ? "" : "--->" + next.sequenceAsString());
    }

    @Override
    public String toString() {
        return format("%s (id=%d)", this.getClass().getSimpleName(), id);
    }

}
