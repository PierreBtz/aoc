package eu.pierrebeitz.aoc._2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc.utils.TestData;
import java.io.BufferedReader;
import org.junit.jupiter.api.Test;

public class Day1Puzzle2Test {

    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(281, new Day1Puzzle2().solve(reader));
    }
}
