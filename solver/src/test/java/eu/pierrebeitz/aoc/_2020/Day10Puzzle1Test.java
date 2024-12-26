package eu.pierrebeitz.aoc._2020;

import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class Day10Puzzle1Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(2020, 10)) {
            var result = Day10Puzzle1.solve(reader.lines());
            assertEquals(220, result);
        }
    }
}
