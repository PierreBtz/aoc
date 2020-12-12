package eu.pierrebeitz.aoc._2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static eu.pierrebeitz.aoc._2020.Day9Puzzle1.XmasSequence.fromStream;
import static org.junit.jupiter.api.Assertions.*;

class Day9Puzzle1Test {

    @Test
    public void testExample() throws IOException {
        try (var reader = loadTestData()) {
            var xmasSequence = fromStream(reader.lines());
            xmasSequence.setPreambleSize(5);
            assertEquals(127, xmasSequence.validateSequence().get());
        }
    }


    public static BufferedReader loadTestData() {
        return new BufferedReader(
              new InputStreamReader(
                    Day8Puzzle1Test.class.getResourceAsStream("/eu/pierrebeitz/aoc/_2020/day9-example.txt")
              )
        );
    }

}