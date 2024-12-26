package eu.pierrebeitz.aoc._2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc.utils.TestData;
import java.io.BufferedReader;
import org.junit.jupiter.api.Test;

public class Day4Puzzle2Test {
    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(9, new Day4Puzzle2().solve(reader));
    }
}
