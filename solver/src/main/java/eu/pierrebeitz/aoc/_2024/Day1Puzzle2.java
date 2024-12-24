package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;

import java.io.BufferedReader;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day1Puzzle2 implements DayPuzzle<Long> {

    @Override
    public Long solve(BufferedReader reader) {
        var sortedList = Day1Puzzle1.getSortedLists(reader);

        var secondListMapping = sortedList.second().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return sortedList.first().stream()
                .map(i -> i * secondListMapping.getOrDefault(i, 0L))
                .mapToLong(Long::longValue)
                .sum();
    }
}
