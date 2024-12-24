package eu.pierrebeitz.aoc._2020;

import java.io.IOException;
import java.util.function.IntConsumer;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;

public class Day10Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = loadInputForDay(2020, 10)) {
            System.out.println(solve(reader.lines()));
        }
    }

    static int solve(Stream<String> lines) {
        var puzzleStatistics = lines.map(Integer::valueOf)
              .sorted()
              .collect(
                    Collector.of(
                          PuzzleStatistics::new,
                          PuzzleStatistics::accept,
                          PuzzleStatistics::combine
                    ));
        return puzzleStatistics.getOneDiffCount() * (puzzleStatistics.getThreeDiffCount() + 1);
    }

    static class PuzzleStatistics implements IntConsumer {
        private int oneDiffCount;
        private int threeDiffCount;
        private int lastValue;

        @Override
        public void accept(int value) {
            var diff = value - lastValue;
            if (diff == 1) {
                oneDiffCount++;
            } else if (diff == 3) {
                threeDiffCount++;
            }
            lastValue = value;
        }

        public PuzzleStatistics combine(PuzzleStatistics other) {
            var result = new PuzzleStatistics();
            result.lastValue = this.lastValue + other.lastValue;
            result.oneDiffCount = this.oneDiffCount + other.oneDiffCount;
            result.threeDiffCount = this.threeDiffCount + other.threeDiffCount;
            return result;
        }

        public int getOneDiffCount() {
            return oneDiffCount;
        }

        public int getThreeDiffCount() {
            return threeDiffCount;
        }
    }
}
