package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.TestData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Puzzle1Test {
    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(18, new Day4Puzzle1().solve(reader));
    }
}
