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

class Day7Puzzle1Test {

    @ParameterizedTest
    @MethodSource("generateParseEntryData")
    public void parseEntry(String input, Bag output) {
        assertEquals(output, new Bag(input));
    }

    @ParameterizedTest
    @MethodSource("generateDecomposeBagData")
    public void decomposeBag(Bag input, Map<String, Bag> map, Bag output) {
        input.decomposeInto(BAG_OF_INTEREST, map);
        assertEquals(output, input);
    }

    private static Stream<Arguments> generateParseEntryData() {
        return Stream.of(
                Arguments.of(
                        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                        new Bag("light red", Map.of("bright white", 1, "muted yellow", 2))),
                Arguments.of("faded blue bags contain no other bags.", new Bag("faded blue", Collections.emptyMap())));
    }

    private static Stream<Arguments> generateDecomposeBagData() {
        return Stream.of(Arguments.of(
                new Bag("dark orange", Map.of("bright white", 3, "muted yellow", 4)),
                Map.ofEntries(
                        Map.entry("bright white", new Bag("bright white", Map.of("shiny gold", 1))),
                        Map.entry("muted yellow", new Bag("muted yellow", Map.of("shiny gold", 2, "faded blue", 9))),
                        Map.entry("faded blue", new Bag("faded blue", Collections.emptyMap()))),
                new Bag("dark orange", Map.of("shiny gold", 11))));
    }
}
