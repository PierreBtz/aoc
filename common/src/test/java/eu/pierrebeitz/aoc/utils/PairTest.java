package eu.pierrebeitz.aoc.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PairTest {

    @ParameterizedTest
    @MethodSource("getCombinationsSource")
    void getCombinations(List<String> input, List<Pair<String>> output) {
        assertEquals(output, Pair.getCombinations(input));
    }

    static Stream<Arguments> getCombinationsSource() {
        return Stream.of(
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of("1"), List.of()),
                Arguments.of(List.of("1", "2"), List.of(new Pair<>("1", "2"))),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of(new Pair<>("1", "2"), new Pair<>("1", "3"), new Pair<>("2", "3"))));
    }
}
