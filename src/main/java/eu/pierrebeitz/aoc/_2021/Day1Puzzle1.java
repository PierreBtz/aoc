package eu.pierrebeitz.aoc._2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;

public class Day1Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = loadInputForDay(2021, 1)) {
            System.out.println(compute(reader));
        }
    }

    public static int compute(BufferedReader reader) {
        return reader.lines()
              .map(Integer::parseInt)
              .collect(new ComputeIncreaseCollector());
    }

    // for the fun of writing a collector
    // this is clearly not the intended usage of collectors nor the most optimal solution :)
    public static class ComputeIncreaseCollector implements Collector<Integer, Integer, Integer> {
        private int sum;
        private int previous = Integer.MAX_VALUE;

        @Override
        public Supplier<Integer> supplier() {
            return () -> 0;
        }

        @Override
        public BiConsumer<Integer, Integer> accumulator() {
            return (i, s) -> {
                if (s > previous) {
                    sum++;
                }
                previous = s;
            };
        }

        @Override
        public BinaryOperator<Integer> combiner() {
            return (i1, i2) -> 2;
        }

        @Override
        public Function<Integer, Integer> finisher() {
            return acc -> sum;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }
}
