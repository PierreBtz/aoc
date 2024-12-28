package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day7Puzzle1 implements DayPuzzle<Long> {
    @Override
    public Long solve(BufferedReader reader) {
        return reader.lines()
                .map(Equation::fromString)
                .filter(Equation::isValid)
                .mapToLong(Equation::getResult)
                .sum();
    }

    static class Equation {
        protected final long result;
        protected final Deque<Long> operands;

        Equation(long result, Deque<Long> operands) {
            this.result = result;
            this.operands = operands;
        }

        static Equation fromString(String str) {
            var resultSplit = str.split(":");
            var result = Long.parseLong(resultSplit[0].trim());
            var operands = Arrays.stream(resultSplit[1].split(" "))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(LinkedList::new));
            return new Equation(result, operands);
        }

        protected boolean isValid() {
            return isValid(new LinkedList<>(operands), 0);
        }

        private boolean isValid(Queue<Long> remaining, long currentTotal) {
            if (remaining.isEmpty()) {
                return currentTotal == result;
            }
            if (currentTotal > result) {
                return false;
            }
            var next = remaining.poll();
            return isValid(new LinkedList<>(remaining), currentTotal * next)
                    || isValid(new LinkedList<>(remaining), currentTotal + next);
        }

        public long getResult() {
            return result;
        }

        public Deque<Long> getOperands() {
            return operands;
        }
    }
}
