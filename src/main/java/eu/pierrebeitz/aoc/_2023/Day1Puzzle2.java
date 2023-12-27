package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;

import java.io.BufferedReader;
import java.util.Map;
import java.util.regex.Pattern;

public class Day1Puzzle2 implements DayPuzzle<Integer> {
    private static final Pattern START_PATTERN = Pattern.compile("^.*?(?<digit>one|two|three|four|five|six|seven|eight|nine)");
    private static final Pattern END_PATTERN = Pattern.compile(".*(?<digit>one|two|three|four|five|six|seven|eight|nine).*?$");
    private static final Map<String, String> SUBSTITUTIONS = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    @Override
    public Integer solve(BufferedReader reader) {
        return reader.lines()
                .map(Day1Puzzle2::preprocessToNumber)
                .map(Day1Puzzle1::processLine)
                .mapToInt(i -> i)
                .sum();
    }

    private static String preprocessToNumber(String line) {
        System.err.printf("Original line: %s%n", line);
                var startMatcher = START_PATTERN.matcher(line);
        if(startMatcher.find()) {
            var startMatch = startMatcher.group("digit");
            line = line.replaceFirst(startMatch, SUBSTITUTIONS.get(startMatch));
        }
        var endMatcher = END_PATTERN.matcher(line);
        if(endMatcher.find()) {
            var endMatch = endMatcher.group("digit");
            line = line.replaceFirst(endMatch, SUBSTITUTIONS.get(endMatch));
        }
        System.err.printf("Transformed line: %s%n", line);
        return line;
    }
}
