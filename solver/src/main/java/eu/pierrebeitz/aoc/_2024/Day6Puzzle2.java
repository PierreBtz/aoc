package eu.pierrebeitz.aoc._2024;

import static eu.pierrebeitz.aoc._2024.Day6Puzzle1.Map.OBSTACLE;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;

public class Day6Puzzle2 implements DayPuzzle<Integer> {

    @Override
    public Integer solve(BufferedReader reader) {
        var map = new Day6Puzzle1.Map(reader);
        System.err.printf("Parsed map:%n%s%n", map);
        return mutateAndAnalyze(map);
    }

    static int mutateAndAnalyze(Day6Puzzle1.Map origin) {
        var it = origin.iterator();
        var validMutations = 0;
        var analyzed = 0;
        var total = origin.rowSize() * origin.columnSize();
        while (it.hasNext()) {
            var node = it.next();
            analyzed++;
            if (node.getValue() == OBSTACLE) {
                continue;
            }
            var mutation = new Day6Puzzle1.Map(origin);
            mutation.getAt(node.getRow(), node.getCol()).setValue(OBSTACLE);
            validMutations += mutation.startMovingAndDetectCycle() ? 1 : 0;
            System.err.printf("Analyzed/Valid/Total %d/%d/%d%n", analyzed, validMutations, total);
        }
        return validMutations;
    }
}
