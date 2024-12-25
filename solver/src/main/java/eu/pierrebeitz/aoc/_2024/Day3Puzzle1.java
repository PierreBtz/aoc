package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Day3Puzzle1 implements DayPuzzle<Integer> {
    private static final Pattern MULT_PATTERN = Pattern.compile("mul\\((?<operand1>\\d{1,3}),(?<operand2>\\d{1,3})\\)");

    @Override
    public Integer solve(BufferedReader reader) {
        var total = 0;
        try {
            var line = reader.readLine();
            while (line != null) {
                var match = MULT_PATTERN.matcher(line);

                while (match.find()) {
                    var operand1 = Integer.parseInt(match.group("operand1"));
                    var operand2 = Integer.parseInt(match.group("operand2"));
                    total += operand1 * operand2;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
