package eu.pierrebeitz.aoc._2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static eu.pierrebeitz.aoc._2021.Day1Puzzle2.compute;
import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;

public class Day1Puzzle2Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadInputForDay(2021, 1)) {
            Assertions.assertEquals(5, compute(reader));
        }
    }
}
