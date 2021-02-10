package hva.ads.college.week02_recursion.train;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class FreightWagon extends Wagon<FreightWagon> {

    private final int maximumWeight;

    public FreightWagon(int id, int maximumWeight) {
        super(id);
        this.maximumWeight = maximumWeight;
    }

    public int getTotalWeight() {
        // TODO implementation
        return 0;
    }

    @Override
    public String toString() {
        return format("%s maximumWeight=%d", super.toString(), maximumWeight);
    }
}
