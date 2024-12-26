package eu.pierrebeitz.aoc._2020;

import static eu.pierrebeitz.aoc._2020.Day7Puzzle1.BAG_OF_INTEREST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc._2020.Day7Puzzle1.Bag;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day7Puzzle2Test {

    @ParameterizedTest
    @MethodSource("generateCountNumberofPrimeBagsData")
    public void countNumberofPrimeBags(Bag input, Map<String, Bag> map, int output) {
        assertEquals(output, input.countNumberofPrimeBags(map));
    }

    private static Stream<Arguments> generateCountNumberofPrimeBagsData() {
        return Stream.of(Arguments.of(
                new Bag(BAG_OF_INTEREST, Map.of("dark olive", 1, "vibrant plum", 2)),
                Map.ofEntries(
                        Map.entry("dark olive", new Bag("dark olive", Map.of("faded blue", 3, "dotted black", 4))),
                        Map.entry("faded blue", new Bag("faded blue", Collections.emptyMap())),
                        Map.entry("dotted black", new Bag("dotted black", Collections.emptyMap())),
                        Map.entry("vibrant plum", new Bag("vibrant plum", Map.of("faded blue", 5, "dotted black", 6)))),
                32));
    }
}
