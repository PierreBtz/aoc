package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5Puzzle1 implements DayPuzzle<Integer> {

    @Override
    public Integer solve(BufferedReader reader) {
        var rules = new Rules();
        var updates = new ArrayList<List<Integer>>();
        parseInput(reader, rules, updates);

        System.err.println("----Starting analysis----");
        return updates.stream()
                .filter(update -> isInRightOrder(update, rules))
                .mapToInt(update -> update.get(update.size() / 2))
                .sum();
    }

    static void parseInput(BufferedReader reader, Rules rules, ArrayList<List<Integer>> updates) {
        try {
            var line = reader.readLine();
            var inRuleSection = true;
            System.err.println("----Parsing rules section----");
            while (line != null) {
                if (line.isEmpty()) {
                    inRuleSection = false;
                    System.err.println("----Parsing update section----");
                } else if (inRuleSection) {
                    var split = line.split("\\|");
                    var part1 = Integer.parseInt(split[0]);
                    var part2 = Integer.parseInt(split[1]);
                    rules.addRule(part1, part2);
                    System.err.printf("New rule added %d | %d%n", part1, part2);
                } else {
                    var update = Arrays.stream(line.split(","))
                            .map(Integer::parseInt)
                            .toList();
                    updates.add(update);
                    System.err.printf("New update added %s%n", update);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isInRightOrder(List<Integer> update, Rules rules) {
        // ideally we would compare the array to a sorted array, however there is no guarantee
        // this will work since some comparisons might be undefined
        System.err.printf("Starting analysis of update %s%n", update);
        for (int i = 0; i < update.size(); i++) {
            var page = update.get(i);
            System.err.printf("Starting analysis of page %d%n", page);
            for (var j = 0; j < i; j++) {
                var before = update.get(j);
                var beforeRules = rules.beforeRules.get(page);
                if (beforeRules != null && beforeRules.contains(before)) {
                    System.err.printf("Found that page %d is before page %d which is invalid%n", before, page);
                    return false;
                }
            }
            for (var j = i + 1; j < update.size(); j++) {
                var after = update.get(j);
                var afterRules = rules.afterRules.get(page);
                if (afterRules != null && afterRules.contains(after)) {
                    System.err.printf("Found that page %d is after page %d which is invalid%n", after, page);
                    return false;
                }
            }
        }
        return true;
    }

    static class Rules {
        private final Map<Integer, List<Integer>> beforeRules;
        private final Map<Integer, List<Integer>> afterRules;

        Rules() {
            beforeRules = new HashMap<>();
            afterRules = new HashMap<>();
        }

        void addRule(int part1, int part2) {
            addValue(part1, part2, beforeRules);
            addValue(part2, part1, afterRules);
        }

        void addValue(int key, int value, Map<Integer, List<Integer>> map) {
            var list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(value);
        }

        public Map<Integer, List<Integer>> getBeforeRules() {
            return beforeRules;
        }

        public Map<Integer, List<Integer>> getAfterRules() {
            return afterRules;
        }
    }
}
