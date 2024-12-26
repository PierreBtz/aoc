package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;
import java.io.IOException;

public class Day5Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 5)) {
            System.out.println(reader.lines()
                    .map(Seat::fromString)
                    .map(Seat::getId)
                    .mapToInt(i -> i)
                    .max()
                    .orElse(-1));
        }
    }

    static class Seat {
        private static final int ROW_COUNT = 128;
        private static final int COLUMN_COUNT = 8;
        private static final char ROW_BACK = 'B';
        private static final char ROW_FRONT = 'F';
        private static final char COLUMN_LEFT = 'L';
        private static final char COLUMN_RIGHT = 'R';

        private final int row;
        private final int column;

        private Seat(int row, int column) {
            this.row = row;
            this.column = column;
        }

        static Seat fromString(String str) {
            var row = navigateSeats(str, ROW_COUNT, ROW_FRONT, ROW_BACK);
            var column = navigateSeats(str, COLUMN_COUNT, COLUMN_LEFT, COLUMN_RIGHT);
            return new Seat(row, column);
        }

        private static int navigateSeats(String str, int initialMax, char navigateLow, char navigateUp) {
            var min = 0;
            var max = initialMax;
            for (char c : str.toCharArray()) {
                var pivot = min + (max - min) / 2;
                if (c == navigateLow) {
                    max = pivot;
                } else if (c == navigateUp) {
                    min = pivot;
                }
                // else do nothing, the navigation code is not for us
            }
            return min;
        }

        int getId() {
            return row * COLUMN_COUNT + column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }
}
