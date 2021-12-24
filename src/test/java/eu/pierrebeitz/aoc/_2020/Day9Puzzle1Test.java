package eu.pierrebeitz.aoc._2020;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static eu.pierrebeitz.aoc._2020.Day9Puzzle1.XmasSequence.fromStream;
import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Puzzle1Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(2020, 9)) {
            var xmasSequence = fromStream(reader.lines());
            xmasSequence.setPreambleSize(5);
            assertEquals(127, xmasSequence.validateSequence().get());
        }
    }

}