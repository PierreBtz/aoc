package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;

public class Day1Puzzle1 implements DayPuzzle<Integer> {

    @Override
    public Integer solve(BufferedReader reader) {
        return reader.lines().map(Day1Puzzle1::processLine).mapToInt(i -> i).sum();
    }

    static int processLine(String line) {
        System.err.printf("Original line: %s%n", line);
        var intsInLine = line.chars()
                .filter(i -> i >= 48 && i < 58)
                .map(i -> i - 48)
                .boxed()
                .toList();
        int result = intsInLine.getFirst() * 10 + intsInLine.getLast();
        System.err.printf("Transformed line: %s%n", result);
        return result;
    }
}
