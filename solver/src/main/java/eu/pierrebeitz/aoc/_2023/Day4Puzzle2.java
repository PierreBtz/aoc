package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day4Puzzle2 implements DayPuzzle<Integer> {

    @Override
    public Integer solve(BufferedReader reader) {
        var calculator = new ScratchCardCalculator();
        reader.lines()
                .map(Day4Puzzle1.Card::fromString)
                .peek(System.err::println)
                .collect(Collectors.toMap(c -> c, Day4Puzzle1.Card::winningNumberCount, (c1, c2) -> c1, TreeMap::new))
                .forEach((key, value) -> computeNewCards(calculator, key, value));
        return calculator.result();
    }

    static void computeNewCards(ScratchCardCalculator cardCalculator, Day4Puzzle1.Card card, int winningCount) {
        var id = card.id();
        var copies = cardCalculator.addCards(id, 1);
        System.err.printf("Card %d: copies: %d%n", id, copies);
        for (var i = 0; i < winningCount; i++) {
            cardCalculator.addCards(id + i + 1, copies);
        }
        System.err.println(cardCalculator.map);
    }

    static class ScratchCardCalculator {
        private final Map<Integer, Integer> map;

        ScratchCardCalculator() {
            map = new HashMap<>();
        }

        int addCards(int id, int count) {
            return map.merge(id, count, Integer::sum);
        }

        int result() {
            return map.values().stream().mapToInt(i -> i).sum();
        }
    }
}
