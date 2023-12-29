package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.TestData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Puzzle2Test {

    @Test
    void parser(@TestData BufferedReader reader) {
        assertEquals(30, new Day4Puzzle2().solve(reader));
    }
}
