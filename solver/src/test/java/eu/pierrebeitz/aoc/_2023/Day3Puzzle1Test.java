package eu.pierrebeitz.aoc._2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc.utils.TestData;
import java.io.BufferedReader;
import org.junit.jupiter.api.Test;

class Day3Puzzle1Test {

    @Test
    void parser(@TestData BufferedReader reader) {
        assertEquals(4361, new Day3Puzzle1().solve(reader));
    }
}
