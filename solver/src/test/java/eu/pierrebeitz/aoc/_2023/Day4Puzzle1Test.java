package eu.pierrebeitz.aoc._2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc.utils.TestData;
import java.io.BufferedReader;
import org.junit.jupiter.api.Test;

public class Day4Puzzle1Test {

    @Test
    void parser(@TestData BufferedReader reader) {
        assertEquals(13, new Day4Puzzle1().solve(reader));
    }
}
