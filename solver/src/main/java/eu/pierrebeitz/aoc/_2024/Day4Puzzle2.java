package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4Puzzle2 implements DayPuzzle<Integer> {
    @Override
    public Integer solve(BufferedReader reader) {
        var gridLines = reader.lines()
                .map(line -> line.chars().mapToObj(i -> (char) i).toList())
                .toList();

        var grid = new XGrid(gridLines);

        var matchingX = 0;
        var it = grid.iterator();
        while (it.hasNext()) {
            var line = it.getLine();
            var column = it.getColumn();
            System.err.printf("Analysing cell %d %d%n", line, column);
            if (grid.hasX(line, column)) {
                matchingX++;
            }
            it.next();
        }
        return matchingX;
    }

    static class XGrid extends Day4Puzzle1.Grid {

        XGrid(List<List<Character>> cells) {
            super(cells);
        }

        boolean hasX(int line, int column) {
            var cell = cells.get(line).get(column);
            if (cell != 'A') {
                return false;
            }
            // an X has two parts: a slash / and a backslash \
            var mAndS = Set.of('M', 'S');
            return computeSlash(line, column).containsAll(mAndS)
                    && computeBackSlash(line, column).containsAll(mAndS);
        }

        Set<Character> computeSlash(int line, int column) {
            var result = new HashSet<Character>();
            if (inGrid(line + 1, column - 1)) {
                result.add(cells.get(line + 1).get(column - 1));
            }
            if (inGrid(line - 1, column + 1)) {
                result.add(cells.get(line - 1).get(column + 1));
            }
            return result;
        }

        Set<Character> computeBackSlash(int line, int column) {
            var result = new HashSet<Character>();
            if (inGrid(line - 1, column - 1)) {
                result.add(cells.get(line - 1).get(column - 1));
            }
            if (inGrid(line + 1, column + 1)) {
                result.add(cells.get(line + 1).get(column + 1));
            }
            return result;
        }
    }
}
