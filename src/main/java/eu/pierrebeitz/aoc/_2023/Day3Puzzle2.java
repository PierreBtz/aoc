package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Matrix;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day3Puzzle2 implements DayPuzzle<Integer> {

    @Override
    public Integer solve(BufferedReader reader) {
        var matrix = new Matrix(reader);

        var it = matrix.iterator();
        var currentValue = "";
        var endOfValue = false;
        var gearNeighbours = new HashSet<Matrix.Node>();
        var gearMap = new HashMap<Matrix.Node, List<String>>();

        while (it.hasNext()) {
            var node = it.next();
            var value = node.getValue();
            if (Character.isDigit(value)) {
                var digit = Integer.parseInt(Character.valueOf(value).toString());
                currentValue += digit;
                // if we're done with a row then value is complete
                endOfValue = node.getCol() == matrix.columnSize() - 1;
                gearNeighbours.addAll(getGearNeighbour(matrix.getNeighbours(node)));
            } else {
                endOfValue = true;
            }
            if (endOfValue) {
                if (!gearNeighbours.isEmpty()) {
                    for (var gear : gearNeighbours) {
                        if (gearMap.get(gear) != null) {
                            gearMap.get(gear).add(currentValue);
                        } else {
                            var values = new ArrayList<String>();
                            values.add(currentValue);
                            gearMap.put(gear, values);
                        }
                    }
                }
                currentValue = "";
                endOfValue = false;
                gearNeighbours.clear();
            }
        }

        System.err.printf("Gear map: %s%n", gearMap);

        return gearMap.entrySet().stream()
                .filter(e -> e.getValue().size() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .values().stream()
                .map(Day3Puzzle2::ratio)
                .mapToInt(i -> i)
                .sum();
    }

    private static int ratio(List<String> str) {
        assert str.size() == 2;
        return Integer.parseInt(str.get(0)) * Integer.parseInt(str.get(1));
    }

    private static List<Matrix.Node> getGearNeighbour(List<Matrix.Node> nodes) {
        return nodes.stream()
                .filter(n -> isGear(n.getValue()))
                .toList();
    }

    private static boolean isGear(char c) {
        return c == '*';
    }
}
