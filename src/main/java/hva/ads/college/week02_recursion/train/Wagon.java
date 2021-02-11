package hva.ads.college.week02_recursion.train;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class Wagon<W extends Wagon<W>> {

    protected W next;

    protected W previous;

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
    public W getLastWagon() {
        return next == null ? (W) this : next.getLastWagon();
    }

    @SuppressWarnings("unchecked cast")
    public W getFirstWagon() {
        return previous == null ? (W) this : previous.getFirstWagon();
    }

    @SuppressWarnings("unchecked cast")
    public W moveTailToFront() {
        W firstWagon = getFirstWagon();
        W lastWagon = getLastWagon();

        if (firstWagon != lastWagon) {
            W previousTolast = lastWagon.previous;
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
    public W addWagon(W wagon) {
        next = wagon;
        wagon.previous = (W) this;
        return wagon;
    }

    public W reverse() {
        W lastWagon = getLastWagon();
        if (this == lastWagon) return (W) this;

        W previousTolast = lastWagon.previous;
        // Disconnect last. Always 2 operations
        previousTolast.next = null;
        lastWagon.previous = null;
        // 1 -> 2 -> 3 -> 4
        // 4  1 -> 2 -> 3

        W secondWagon = reverse();

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
