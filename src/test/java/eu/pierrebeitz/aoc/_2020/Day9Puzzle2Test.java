package eu.pierrebeitz.aoc._2020;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static eu.pierrebeitz.aoc._2020.Day9Puzzle1.XmasSequence.fromStream;
import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Puzzle2Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(9)) {
            var xmasSequence = fromStream(reader.lines());
            xmasSequence.setPreambleSize(5);
            assertEquals(62, xmasSequence.findWeakness());
        }
    }

}