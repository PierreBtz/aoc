package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day2Puzzle1 implements DayPuzzle<Long> {
    @Override
    public Long solve(BufferedReader reader) {
        return reader.lines().map(Report::fromInput).filter(Report::isValid).count();
    }

    record Level(int value) {

        static Level fromString(String s) {
            return new Level(Integer.parseInt(s));
        }
    }

    static class Report {
        private final Queue<Day2Puzzle1.Level> levels;

        Report(Queue<Level> levels) {
            this.levels = levels;
        }

        public Queue<Level> getLevels() {
            return levels;
        }

        static Report fromInput(String line) {
            return new Report(Arrays.stream(line.split(" "))
                    .map(Level::fromString)
                    .collect(Collectors.toCollection(LinkedList::new)));
        }

        boolean isValid() {
            if (levels.isEmpty() || levels.size() == 1) {
                return true;
            }

            var current = levels.poll();
            var next = levels.poll();
            var diff = current.value - next.value;

            if (isInvalidStep(diff)) {
                return false;
            }

            var increases = diff < 0;
            while (next != null) {
                current = next;
                next = levels.poll();
                if (next == null) {
                    return true;
                }
                diff = current.value - next.value;
                if (isInvalidStep(diff)) {
                    return false;
                }
                if (increases && diff > 0 || !increases && diff < 0) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isInvalidStep(int diff) {
            return diff == 0 || Math.abs(diff) > 3;
        }
    }
}
