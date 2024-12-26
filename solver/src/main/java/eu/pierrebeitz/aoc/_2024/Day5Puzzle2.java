package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static eu.pierrebeitz.aoc._2024.Day5Puzzle1.isInRightOrder;

public class Day5Puzzle2 implements DayPuzzle<Integer> {
    @Override
    public Integer solve(BufferedReader reader) {
        var rules = new Day5Puzzle1.Rules();
        var updates = new ArrayList<List<Integer>>();
        Day5Puzzle1.parseInput(reader, rules, updates);

        System.err.println("----Starting analysis----");
        return updates.stream()
                .filter(update -> !isInRightOrder(update, rules))
                .map(update -> update.stream().sorted(new UpdateComparator(rules)).toList())
                .mapToInt(update -> update.get(update.size() / 2))
                .sum();
    }

    static class UpdateComparator implements Comparator<Integer> {
        private final Day5Puzzle1.Rules rules;

        UpdateComparator(Day5Puzzle1.Rules rules) {
            this.rules = rules;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if (Objects.equals(o1, o2)) {
                return 0;
            }

            var afterO1 = rules.getBeforeRules().get(o1);
            var beforeO1 = rules.getAfterRules().get(o1);

            if (afterO1 != null && afterO1.contains(o2)) {
                return -1;
            } else if (beforeO1 != null && beforeO1.contains(o2)) {
                return 1;
            }
            return 0;
        }
    }
}
