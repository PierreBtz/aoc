package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(3)) {
            var env = new Environment(reader.lines());
            var treeCount = 0;
            while (env.canMove()) {
                treeCount += env.moveAndIsTree();
            }
            System.out.println(treeCount);
        }
    }

    static class Environment {
        private static final int VERTICAL_SLOPE = 3;
        private static final int HORIZONTAL_SLOPE = 1;

        private final Map map;
        private Position position;

        Environment(Stream<String> inputs) {
            map = new Map(inputs);
            position = new Position(0, 0);
        }

        boolean canMove() {
            return map.canMove(position, HORIZONTAL_SLOPE);
        }

        int moveAndIsTree() {
            position = map.move(position, VERTICAL_SLOPE, HORIZONTAL_SLOPE);
            return map.isTree(position) ? 1 : 0;
        }
    }

    static class Map {
        private static final char TREE_CHAR = '#';

        private final List<String> grid;
        private final int columnCount;
        private final int lineCount;

        Map(Stream<String> inputs) {
            grid = inputs.collect(Collectors.toList());
            columnCount = grid.get(0).length();
            lineCount = grid.size();
        }

        Position move(Position position, int dx, int dy) {
            return new Position((position.getX() + dx) % columnCount, position.getY() + dy);
        }

        boolean canMove(Position position, int dy) {
            return position.getY() + dy < lineCount;
        }

        boolean isTree(Position position) {
            var isTree = grid.get(position.getY()).charAt(position.getX()) == TREE_CHAR;
            System.err.println(position + "\n isTree: " + isTree);
            return isTree;
        }
    }

    static class Position {
        private int x;
        private int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        @Override
        public String toString() {
            return "Position{" +
                  "x=" + x +
                  ", y=" + y +
                  '}';
        }

        public int getY() {
            return y;
        }
    }
}
