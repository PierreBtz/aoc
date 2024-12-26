package eu.pierrebeitz.aoc._2021;

import static eu.pierrebeitz.aoc._2021.Day1Puzzle1.compute;
import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day1Puzzle1Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(2021, 1)) {
            Assertions.assertEquals(7, compute(reader));
        }
    }
}
