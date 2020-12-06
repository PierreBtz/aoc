package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(6)) {
            var count = AocUtils.readByGroupAndMapToObject(reader, Group::new).stream()
                  .peek(System.err::println)
                  .map(Group::getQuestionCount)
                  .reduce(0, Integer::sum);
            System.out.println(count);

        }
    }

    static class Group {
        private final Set<Character> allAnswers;

        private Group(String str) {
            allAnswers = str.chars()
                  .mapToObj(c -> (char) c)
                  .filter(c -> c != '\n')
                  .collect(Collectors.toSet());
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
}
