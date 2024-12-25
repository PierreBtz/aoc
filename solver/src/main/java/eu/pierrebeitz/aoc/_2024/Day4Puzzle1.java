package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day4Puzzle1 implements DayPuzzle<Integer> {
    private static final Queue<Character> WORD = new LinkedList<>(List.of('X', 'M', 'A', 'S'));

    @Override
    public Integer solve(BufferedReader reader) {
        var gridLines = reader.lines()
                .map(line -> line.chars().mapToObj(i -> (char) i).toList())
                .toList();

        var grid = new Grid(gridLines);

        var matchingWords = 0;
        var it = grid.iterator();
        while (it.hasNext()) {
            var line = it.getLine();
            var column = it.getColumn();
            System.err.printf("Analysing cell %d %d%n", line, column);
            matchingWords += grid.matchingWordsForCell(line, column);
            it.next();
        }

        return matchingWords;
    }

    static class Grid implements Iterable<Character> {
        protected final List<List<Character>> cells;

        Grid(List<List<Character>> cells) {
            this.cells = cells;
        }

        enum Direction {
            N(-1, 0),
            NE(-1, 1),
            E(0, 1),
            SE(1, 1),
            S(1, 0),
            SW(1, -1),
            W(0, -1),
            NW(-1, -1);

            private final int deltaLine;
            private final int deltaColumn;

            Direction(int deltaLine, int deltaColumn) {
                this.deltaLine = deltaLine;
                this.deltaColumn = deltaColumn;
            }

            public int getDeltaLine() {
                return deltaLine;
            }

            public int getDeltaColumn() {
                return deltaColumn;
            }
        }

        int matchingWordsForCell(int line, int column) {
            var matchingWords = 0;
            for (var direction : Direction.values()) {
                if (iterateInDirection(line, column, direction, new LinkedList<>(WORD))) {
                    matchingWords++;
                }
            }
            return matchingWords;
        }

        boolean inGrid(int line, int column) {
            if (line < 0 || column < 0) {
                return false;
            }
            return line < cells.size() && column < cells.get(line).size();
        }

        boolean iterateInDirection(int line, int column, Direction direction, Queue<Character> expected) {
            if (expected.isEmpty()) {
                return true;
            }
            if (!inGrid(line, column)) {
                return false;
            }
            var currentChar = expected.poll();
            var cell = cells.get(line).get(column);
            if (cell != currentChar) {
                return false;
            }
            return iterateInDirection(line + direction.getDeltaLine(), column + direction.getDeltaColumn(), direction, expected);
        }

        @Override
        public GridIterator iterator() {
            return new GridIterator(this);
        }

        static class GridIterator implements Iterator<Character> {
            private final Grid grid;
            private int line = 0;
            private int column = 0;

            GridIterator(Grid grid) {
                this.grid = grid;
            }

            @Override
            public boolean hasNext() {
                return line < grid.cells.size();
            }

            public int getLine() {
                return line;
            }

            public int getColumn() {
                return column;
            }

            private void nextWithinColumnBounds() {
                if (column + 1 >= grid.cells.size()) {
                    line++;
                    column = 0;
                } else {
                    column++;
                }
            }

            @Override
            public Character next() {
                if (hasNext()) {
                    var result = grid.cells.get(line).get(column);
                    nextWithinColumnBounds();
                    return result;
                }
                return null;
            }
        }
    }

}
