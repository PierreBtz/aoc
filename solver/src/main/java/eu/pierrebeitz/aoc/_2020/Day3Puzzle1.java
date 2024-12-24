package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3Puzzle1 {
    private static final Slope SLOPE = new Slope(3, 1);

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 3)) {
            var env = new Environment(reader.lines(), SLOPE);
            System.out.println(env.computeTreeCount());
        }
    }

    static class Environment {
        private static final Position INITIAL_POSITION = new Position(0, 0);

        private final Map map;
        private final Slope slope;
        private Position position;

        Environment(Stream<String> inputs, Slope slope) {
            this(new Map(inputs), slope);
        }

        Environment(Map map, Slope slope) {
            this.map = map;
            position = INITIAL_POSITION;
            this.slope = slope;
        }

        boolean canMove() {
            return map.canMove(position, slope.getDy());
        }

        int moveAndIsTree() {
            position = map.move(position, slope);
            return map.isTree(position) ? 1 : 0;
        }

        long computeTreeCount() {
            var treeCount = 0L;
            while (canMove()) {
                treeCount += moveAndIsTree();
            }
            return treeCount;
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

        Position move(Position position, Slope slope) {
            return new Position((position.getX() + slope.getDx()) % columnCount, position.getY() + slope.getDy());
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
        private final int x;
        private final int y;

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

    static class Slope {
        private final int dx;
        private final int dy;

        Slope(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }

        @Override
        public String toString() {
            return "Slope{" +
                  "dx=" + dx +
                  ", dy=" + dy +
                  '}';
        }
    }
}
