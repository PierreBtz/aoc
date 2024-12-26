package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Matrix;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;

public class Day4Puzzle2 implements DayPuzzle<Integer> {
    @Override
    public Integer solve(BufferedReader reader) {
        var grid = new XGrid(reader);

        var matchingX = 0;
        var it = grid.iterator();
        while (it.hasNext()) {
            var line = it.getRow();
            var column = it.getCol();
            System.err.printf("Analysing cell %d %d%n", line, column);
            if (grid.hasX(line, column)) {
                matchingX++;
            }
            it.next();
        }
        return matchingX;
    }

    static class XGrid extends Matrix {

        public XGrid(BufferedReader reader) {
            super(reader);
        }

        boolean hasX(int row, int column) {
            var cell = getValueAt(row, column);
            if (cell != 'A') {
                return false;
            }
            // an X has two parts: a slash / and a backslash \
            var mAndS = Set.of('M', 'S');
            return computeSlash(row, column).containsAll(mAndS)
                    && computeBackSlash(row, column).containsAll(mAndS);
        }

        Set<Character> computeSlash(int row, int column) {
            var result = new HashSet<Character>();
            if (withinBounds(row, column, Direction.SW)) {
                result.add(getInDirection(row, column, Direction.SW).getValue());
            }
            if (withinBounds(row, column, Direction.NE)) {
                result.add(getInDirection(row, column, Direction.NE).getValue());
            }
            return result;
        }

        Set<Character> computeBackSlash(int row, int column) {
            var result = new HashSet<Character>();
            if (withinBounds(row, column, Direction.NW)) {
                result.add(getInDirection(row, column, Direction.NW).getValue());
            }
            if (withinBounds(row, column, Direction.SE)) {
                result.add(getInDirection(row, column, Direction.SE).getValue());
            }
            return result;
        }
    }
}
