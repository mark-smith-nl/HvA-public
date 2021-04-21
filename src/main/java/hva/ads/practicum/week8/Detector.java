package hva.ads.practicum.week8;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Detector {
    protected static List<List<Integer>> splitIntoBlocks(Set<Integer> lineNumbers) {
        List<List<Integer>> blocks = new ArrayList<>();

        List<Integer> block = null;
        List<Integer> sortedLineNumbers = new ArrayList<>(lineNumbers);
        Collections.sort(sortedLineNumbers);

        for (int lineNumber : sortedLineNumbers) {
            System.out.println(lineNumber);
            if (block  == null || block.get(block.size() - 1) + 1 != lineNumber) {
                block = new ArrayList<>();
                blocks.add(block);
            }
            block.add(lineNumber);
        }
        return blocks;
    }
}
