package eu.pierrebeitz.aoc._2020;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static eu.pierrebeitz.aoc._2020.Day8Puzzle2.loadAndExecute;
import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Puzzle2Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(2020, 8)) {
            assertEquals(8, loadAndExecute(reader));
        }
    }
}