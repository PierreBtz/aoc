package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.util.Deque;
import java.util.LinkedList;

public class Day7Puzzle2 implements DayPuzzle<Long> {
    @Override
    public Long solve(BufferedReader reader) {
        return reader.lines()
                .map(Equation2::fromString)
                .filter(Equation2::isValid)
                .mapToLong(Day7Puzzle1.Equation::getResult)
                .sum();
    }

    static class Equation2 extends Day7Puzzle1.Equation {

        Equation2(Day7Puzzle1.Equation eq) {
            super(eq.getResult(), eq.getOperands());
        }

        static Equation2 fromString(String str) {
            return new Equation2(Day7Puzzle1.Equation.fromString(str));
        }

        @Override
        protected boolean isValid() {
            return isValid(new LinkedList<>(operands), 0);
        }

        private boolean isValid(Deque<Long> remaining, long currentTotal) {
            if (remaining.isEmpty()) {
                return currentTotal == result;
            }
            if (currentTotal > result) {
                return false;
            }
            var next = remaining.poll();
            var concat = Long.parseLong(currentTotal + String.valueOf(next));
            return isValid(new LinkedList<>(remaining), currentTotal * next)
                    || isValid(new LinkedList<>(remaining), currentTotal + next)
                    || isValid(new LinkedList<>(remaining), concat);
        }
    }
}
