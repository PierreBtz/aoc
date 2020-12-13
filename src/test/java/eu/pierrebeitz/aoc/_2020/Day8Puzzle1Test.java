package eu.pierrebeitz.aoc._2020;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static eu.pierrebeitz.aoc._2020.Day8Puzzle1.loadAndExecute;
import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Puzzle1Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(8)) {
            var runtime = loadAndExecute(reader);
            assertEquals(5, runtime.getAccumulator());
        }
    }

}