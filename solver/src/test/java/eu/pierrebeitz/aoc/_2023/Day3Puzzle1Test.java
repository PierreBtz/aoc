package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.TestData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Puzzle1Test {

    @Test
    void parser(@TestData BufferedReader reader) {
        assertEquals(4361, new Day3Puzzle1().solve(reader));
    }

}
