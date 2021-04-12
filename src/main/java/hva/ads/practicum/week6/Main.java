package hva.ads.practicum.week6;

import java.util.Comparator;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {

    public static void main(String[] args) {
        Archer[] archers = new Archer[10];

        Sort.selectionsort(archers, new ArcherComparator());
        Sort.selectionsort(archers, new Comparator<Archer>() {
            @Override
            public int compare(Archer o1, Archer o2) {
                return 0;
            }
        });

    }
}
