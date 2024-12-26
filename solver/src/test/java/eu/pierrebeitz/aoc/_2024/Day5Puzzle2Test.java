package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.TestData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Puzzle2Test {
    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(123, new Day5Puzzle2().solve(reader));
    }
}
