package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4Puzzle1 implements DayPuzzle<Double> {

    @Override
    public Double solve(BufferedReader reader) {
        return reader.lines()
                .map(Card::fromString)
                .map(Card::value)
                .mapToDouble(i -> i)
                .sum();
    }

    record Card(int id, Set<Integer> winningNumbers, Set<Integer> numbers) implements Comparable<Card> {
        private static final Pattern CARD_PATTERN =
                Pattern.compile("^Card .*?(?<id>\\d+): (?<winning>.*) \\| (?<numbers>.*)");

        static Card fromString(String str) {
            var matcher = CARD_PATTERN.matcher(str);
            if (matcher.find()) {
                var id = Integer.parseInt(matcher.group("id"));
                var winningNumbersString = matcher.group("winning");
                var numbersString = matcher.group("numbers");
                var winningNumbers = Arrays.stream(winningNumbersString.trim().split(" "))
                        .filter(s -> s != null && !s.isEmpty())
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());
                var numbers = Arrays.stream(numbersString.split(" "))
                        .filter(s -> s != null && !s.isEmpty())
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());
                return new Card(id, winningNumbers, numbers);
            }
            throw new IllegalArgumentException("Cannot parse the provided string: " + str);
        }

        public double value() {
            var winningNumberCount = winningNumberCount();
            return winningNumberCount == 0 ? 0 : Math.pow(2, winningNumberCount - 1);
        }

        public int winningNumberCount() {
            var numbers = new HashSet<>(numbers());
            for (var n : winningNumbers()) {
                numbers.remove(n);
            }
            return numbers().size() - numbers.size();
        }

        @Override
        public int compareTo(Card o) {
            return id - o.id;
        }
    }
}
