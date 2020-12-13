package eu.pierrebeitz.aoc._2020;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Puzzle1Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(10)) {
            var result = Day10Puzzle1.solve(reader.lines());
            assertEquals(220, result);
        }
    }

}