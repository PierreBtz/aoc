package eu.pierrebeitz.aoc._2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc.utils.TestData;
import java.io.BufferedReader;
import org.junit.jupiter.api.Test;

public class Day7Puzzle2Test {
    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(11387, new Day7Puzzle2().solve(reader));
    }
}
