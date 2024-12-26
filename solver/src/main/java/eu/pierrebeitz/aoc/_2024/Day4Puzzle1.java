package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Matrix;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day4Puzzle1 implements DayPuzzle<Integer> {
    private static final Queue<Character> WORD = new LinkedList<>(List.of('X', 'M', 'A', 'S'));

    @Override
    public Integer solve(BufferedReader reader) {
        var grid = new Grid(reader);

        var matchingWords = 0;
        var it = grid.iterator();
        while (it.hasNext()) {
            var line = it.getRow();
            var column = it.getCol();
            System.err.printf("Analysing cell %d %d%n", line, column);
            matchingWords += grid.matchingWordsForCell(line, column);
            it.next();
        }

        return matchingWords;
    }

    static class Grid extends Matrix {

        public Grid(BufferedReader reader) {
            super(reader);
        }

        int matchingWordsForCell(int row, int column) {
            var matchingWords = 0;
            for (var direction : Direction.values()) {
                if (iterateInDirection(row, column, direction, new LinkedList<>(WORD))) {
                    matchingWords++;
                }
            }
            return matchingWords;
        }

        boolean iterateInDirection(int row, int column, Direction direction, Queue<Character> expected) {
            if (expected.isEmpty()) {
                return true;
            }
            if (!withinBounds(row, column)) {
                return false;
            }
            var currentChar = expected.poll();
            var node = getAt(row, column);
            if (node.getValue() != currentChar) {
                return false;
            }
            return iterateInDirection(row + direction.getDeltaRow(), column + direction.getDeltaColumn(), direction, expected);
        }
    }
}
