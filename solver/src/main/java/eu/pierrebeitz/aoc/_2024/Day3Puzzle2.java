package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Day3Puzzle2 implements DayPuzzle<Integer> {
    private static final Pattern PATTERN = Pattern.compile(
            "(?<mul>mul\\((?<operand1>\\d{1,3}),(?<operand2>\\d{1,3})\\))|(?<do>do\\(\\))|(?<dont>don't\\(\\))");

    @Override
    public Integer solve(BufferedReader reader) {
        var total = 0;
        try {
            var line = reader.readLine();
            var skip = false;
            while (line != null) {
                var match = PATTERN.matcher(line);

                while (match.find()) {
                    var _do = match.group("do");
                    if (_do != null) {
                        System.err.println("do found");
                        skip = false;
                        continue;
                    }
                    if (skip) {
                        System.err.println("No do found, skipping the rest of this match");
                        continue;
                    }
                    var dont = match.group("dont");
                    if (dont != null) {
                        System.err.println("don't found");
                        skip = true;
                        continue;
                    }
                    // at this point we are sure we can only match a multiplication
                    var operand1 = Integer.parseInt(match.group("operand1"));
                    var operand2 = Integer.parseInt(match.group("operand2"));
                    System.err.printf("Mult found %d * %d%n%n", operand1, operand2);
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
