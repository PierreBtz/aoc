package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.TestData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Puzzle2Test {

    @Test
    void testExample(@TestData BufferedReader reader) {
        assertEquals(2286, new Day2Puzzle2().solve(reader));
    }
}
