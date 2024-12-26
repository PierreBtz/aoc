package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Matrix;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Day3Puzzle1 implements DayPuzzle<Integer> {

    @Override
    public Integer solve(BufferedReader reader) {
        var matrix = new Matrix(reader);

        var it = matrix.iterator();
        var currentValue = "";
        var endOfValue = false;
        var keepValue = false;
        List<String> numbersToKeep = new ArrayList<>();
        while (it.hasNext()) {
            var node = it.next();
            var value = node.getValue();
            if (Character.isDigit(value)) {
                var digit = Integer.parseInt(Character.valueOf(value).toString());
                currentValue += digit;
                // if we're done with a row then value is complete
                endOfValue = node.getCol() == matrix.columnSize() - 1;
                if (!keepValue) {
                    keepValue = hasValidSymbol(matrix.getNeighbours(node));
                }
            } else {
                endOfValue = true;
            }
            if (endOfValue) {
                if (keepValue) {
                    numbersToKeep.add(currentValue);
                }
                keepValue = false;
                endOfValue = false;
                currentValue = "";
            }
        }
        return numbersToKeep.stream().mapToInt(Integer::parseInt).sum();
    }

    private static boolean hasValidSymbol(List<Matrix.Node> nodes) {
        return nodes.stream()
                .map(Matrix.Node::getValue)
                .anyMatch(c -> !(Character.isLetterOrDigit(c) || Character.isWhitespace(c) || c == '.'));
    }
}
