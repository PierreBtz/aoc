package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6Puzzle1 {

    public static void main(String[] args) throws IOException {
        computeCount(new Puzzle1GroupBuilder());
    }

    static void computeCount(Function<String, Group> groupBuilder) throws IOException {
        try (var reader = AocUtils.loadInputForDay(6)) {
            var count = AocUtils.readByGroupAndMapToObject(reader, groupBuilder).stream()
                  .peek(System.err::println)
                  .map(Group::getQuestionCount)
                  .reduce(0, Integer::sum);
            System.out.println(count);
        }
    }

    static class Group {
        private final Set<Character> allAnswers;

        Group(Set<Character> allAnswers) {
            this.allAnswers = Collections.unmodifiableSet(allAnswers);
        }

        int getQuestionCount() {
            return allAnswers.size();
        }

        @Override
        public String toString() {
            return "Group{" +
                  "allAnswers=" + allAnswers +
                  '}';
        }
    }

    private static class Puzzle1GroupBuilder implements Function<String, Group> {

        @Override
        public Group apply(String s) {
            return new Group(s.chars()
                  .mapToObj(c -> (char) c)
                  .filter(c -> c != '\n')
                  .collect(Collectors.toSet()));
        }
    }
}
