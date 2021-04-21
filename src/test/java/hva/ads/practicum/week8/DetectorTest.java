package hva.ads.practicum.week8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DetectorTest {

    @DisplayName("Testing method splitIntoBlocks")
    @ParameterizedTest
    @MethodSource("splitIntoBlocks")
    public void splitIntoBlocks(Set<Integer> lineNumbers, List<List<Integer>> expectedCodeBlocks) {
        Detector.splitIntoBlocks(lineNumbers);
        List<Integer> codeBlockLineNumbers = getCodeBlockLineNumbers(1, 10);
        System.out.println();

    }

    private static Stream<Arguments> splitIntoBlocks() {

        return Stream.of(
                Arguments.of(fromBlocks(getCodeBlockLineNumbers(197, 10),
                        getCodeBlockLineNumbers(6, 10),
                        getCodeBlockLineNumbers(198, 10),
                        getCodeBlockLineNumbers(8, 10),
                        getCodeBlockLineNumbers(13, 10),
                        getCodeBlockLineNumbers(11, 10),
                        getCodeBlockLineNumbers(4, 10),
                        getCodeBlockLineNumbers(9, 10),
                        getCodeBlockLineNumbers(16, 10),
                        getCodeBlockLineNumbers(196, 10),
                        getCodeBlockLineNumbers(7, 10),
                        getCodeBlockLineNumbers(18, 10),
                        getCodeBlockLineNumbers(12, 10),
                        getCodeBlockLineNumbers(195, 10),
                        getCodeBlockLineNumbers(17, 10),
                        getCodeBlockLineNumbers(14, 10),
                        getCodeBlockLineNumbers(10, 10),
                        getCodeBlockLineNumbers(15, 10),
                        getCodeBlockLineNumbers(1, 10),
                        getCodeBlockLineNumbers(5, 10),
                        getCodeBlockLineNumbers(2, 10),
                        getCodeBlockLineNumbers(3, 10)), null )
        );
    }

    @SafeVarargs
    private static Set<Integer> fromBlocks(List<Integer>... lists) {
        Set<Integer> set = new HashSet<>();
        for (List<Integer> list : lists) set.addAll(list);
        return set;
    }

    private static List<Integer> getCodeBlockLineNumbers(int start, int length) {
        return IntStream.range(start, start + length).boxed().collect(Collectors.toList());
    }
}
