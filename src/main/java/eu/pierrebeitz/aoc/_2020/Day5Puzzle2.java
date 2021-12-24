package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc._2020.Day5Puzzle1.Seat;
import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.summarizingInt;

public class Day5Puzzle2 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 5)) {
            var ids = reader.lines()
                  .map(Seat::fromString)
                  .map(Seat::getId)
                  .collect(Collectors.toList());
            var stats = ids.stream()
                  .collect(summarizingInt(Integer::intValue));
            var max = stats.getMax();
            var min = stats.getMin();
            var allIds = IntStream.range(min, max + 1).boxed();
            var missingIds = allIds
                  .filter(id -> !ids.contains(id))
                  .collect(Collectors.toList());
            assert missingIds.size() == 1 : "We did not get the expected number of id";
            System.out.println(missingIds.get(0));
        }
    }

}
