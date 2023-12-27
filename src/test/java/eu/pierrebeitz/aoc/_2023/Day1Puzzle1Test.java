package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.TestData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Puzzle1Test {

    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(142, new Day1Puzzle1().solve(reader));
    }
}
