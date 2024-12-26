package eu.pierrebeitz.aoc._2020;

import static eu.pierrebeitz.aoc._2020.Day6Puzzle1.computeCount;

import eu.pierrebeitz.aoc._2020.Day6Puzzle1.Group;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6Puzzle2 {

    public static void main(String[] args) throws IOException {
        computeCount(new Puzzle2GroupBuilder());
    }

    static class Puzzle2GroupBuilder implements Function<String, Group> {

        @Override
        public Group apply(String s) {
            var numberOfPeople = s.lines().count();
            var questionSet = s
                    .chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> c != '\n')
                    .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == numberOfPeople)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
            return new Group(questionSet);
        }
    }
}
