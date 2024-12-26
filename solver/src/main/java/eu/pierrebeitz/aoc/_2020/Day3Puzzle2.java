package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc._2020.Day3Puzzle1.Environment;
import eu.pierrebeitz.aoc._2020.Day3Puzzle1.Map;
import eu.pierrebeitz.aoc._2020.Day3Puzzle1.Slope;
import eu.pierrebeitz.aoc.utils.AocUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day3Puzzle2 {
    private static final List<Slope> slopes =
            Arrays.asList(new Slope(1, 1), new Slope(3, 1), new Slope(5, 1), new Slope(7, 1), new Slope(1, 2));

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 3)) {
            var map = new Map(reader.lines());
            var result = slopes.stream()
                    .map(slope -> new Environment(map, slope))
                    .map(Environment::computeTreeCount)
                    .peek(System.err::println)
                    .reduce(1L, (x, y) -> x * y);
            System.out.println(result);
            // not 269104304
        }
    }
}
