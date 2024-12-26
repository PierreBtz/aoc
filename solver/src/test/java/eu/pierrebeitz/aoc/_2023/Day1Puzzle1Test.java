package eu.pierrebeitz.aoc._2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc.utils.TestData;
import java.io.BufferedReader;
import org.junit.jupiter.api.Test;

class Day1Puzzle1Test {

    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(142, new Day1Puzzle1().solve(reader));
    }
}
