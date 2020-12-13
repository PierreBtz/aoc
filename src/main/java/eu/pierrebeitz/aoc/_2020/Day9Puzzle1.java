package eu.pierrebeitz.aoc._2020;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static eu.pierrebeitz.aoc.utils.AocUtils.combinationsOf;
import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;

public class Day9Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = loadInputForDay(9)) {
            var maybeInvalid = XmasSequence.fromStream(reader.lines()).validateSequence();
            maybeInvalid.ifPresent(System.out::println);
        }
    }

    static class XmasSequence {
        private static final int DEFAULT_PREAMBLE_SIZE = 25;
        private final List<Long> sequence;
        private int preambleSize = DEFAULT_PREAMBLE_SIZE;

        private XmasSequence(List<Long> sequence) {
            this.sequence = Collections.unmodifiableList(sequence);
        }

        public void setPreambleSize(int preambleSize) {
            this.preambleSize = preambleSize;
        }

        static XmasSequence fromStream(Stream<String> streamSequence) {
            var sequence = streamSequence.map(Long::valueOf)
                  .collect(Collectors.toList());
            return new XmasSequence(sequence);
        }

        // returns absent if the sequence is valid, otherwise the first number in the list not fullfilling requirements
        Optional<Long> validateSequence() {
            var combinations = computeInitialCombinations();
            for (int i = preambleSize; i < sequence.size(); i++) {
                var acceptableValues = computeAcceptableValues(combinations);
                var currentValue = sequence.get(i);
                if (acceptableValues.contains(currentValue)) {
                    combinations = updateCombination(combinations, sequence.get(i - preambleSize), currentValue);
                } else {
                    return Optional.of(currentValue);
                }
            }
            return Optional.empty();
        }

        long findWeakness() {
            var maybeInvalid = validateSequence();
            if (maybeInvalid.isPresent()) {
                var vulnerabilitySet = Stream.of(sequence.get(0), sequence.get(1)).collect(Collectors.toSet());
                var vulnerabilitySetSum = sequence.get(0) + sequence.get(1);
                var startOfSet = 0;
                var endOfSet = 2;
                while (vulnerabilitySetSum != maybeInvalid.get()) {
                    if (vulnerabilitySetSum < maybeInvalid.get()) {
                        var currentNumber = sequence.get(endOfSet++);
                        vulnerabilitySet.add(currentNumber);
                        vulnerabilitySetSum += currentNumber;
                    } else {
                        var currentNumber = sequence.get(startOfSet++);
                        vulnerabilitySet.remove(currentNumber);
                        vulnerabilitySetSum -= currentNumber;
                    }
                }
                var summary = vulnerabilitySet.stream()
                      .collect(Collectors.summarizingLong(Long::longValue));
                return summary.getMax() + summary.getMin();
            }
            throw new UnsupportedOperationException("This was not expected...");
        }

        private List<List<Long>> computeInitialCombinations() {
            return combinationsOf(sequence.subList(0, preambleSize));
        }

        private List<Long> computeAcceptableValues(List<List<Long>> combinations) {
            return combinations.stream()
                  .map(pair -> pair.get(0) + pair.get(1))
                  .distinct()
                  .collect(Collectors.toList());
        }

        private List<List<Long>> updateCombination(List<List<Long>> combinations, Long valueToRemove, Long valueToAdd) {
            return combinations.stream()
                  .map(value -> {
                      if (valueToRemove.equals(value.get(0))) {
                          return Arrays.asList(valueToAdd, value.get(1));
                      } else if (valueToRemove.equals(value.get(1))) {
                          return Arrays.asList(valueToAdd, value.get(0));
                      }
                      return value;
                  })
                  .collect(Collectors.toList());
        }
    }
}
