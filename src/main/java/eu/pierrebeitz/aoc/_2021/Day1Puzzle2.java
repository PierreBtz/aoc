package eu.pierrebeitz.aoc._2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;

public class Day1Puzzle2 {

    public static void main(String[] args) throws IOException {
        try (var reader = loadInputForDay(2021, 1)) {
            System.out.println(compute(reader));
        }
    }

    public static int compute(BufferedReader reader) {
        return reader.lines()
              .collect(new ComputeIncreaseByWindowCollector());
    }

    public static class ComputeIncreaseByWindowCollector implements Collector<String, List<Integer>, Integer> {
        private static final int NOT_A_MEASURE = -1;
        private int measure1 = NOT_A_MEASURE;
        private int measure2 = NOT_A_MEASURE;
        private int sum;

        @Override
        public Supplier<List<Integer>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<Integer>, String> accumulator() {
            return (l, s) -> {
                var current = Integer.parseInt(s);
                if (isMeasureEmpty(measure1)) {
                    measure1 = current;
                } else if (isMeasureEmpty(measure2)) {
                    measure2 = current;
                } else {
                    l.add(measure1 + measure2 + current);
                    measure1 = measure2;
                    measure2 = current;
                }
            };
        }

        private static boolean isMeasureEmpty(int measure) {
            return measure == NOT_A_MEASURE;
        }

        @Override
        public BinaryOperator<List<Integer>> combiner() {
            return (l1, l2) -> Stream.concat(l1.stream(), l2.stream()).toList();
        }

        @Override
        public Function<List<Integer>, Integer> finisher() {
            return l -> l.stream().collect(new Day1Puzzle1.ComputeIncreaseCollector());
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }
}
