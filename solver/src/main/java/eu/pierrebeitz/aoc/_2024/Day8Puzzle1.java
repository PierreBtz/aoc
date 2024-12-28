package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Matrix;
import eu.pierrebeitz.aoc.utils.Pair;
import java.io.BufferedReader;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Day8Puzzle1 implements DayPuzzle<Long> {
    @Override
    public Long solve(BufferedReader reader) {
        return new Map(reader).computeAntiNodeSize();
    }

    static class Map extends Matrix {

        public Map(BufferedReader reader) {
            super(reader);
        }

        public Map(Map map) {
            super(map);
        }

        long computeAntiNodeSize() {
            var sortedNodes = getSortedNodes();

            return sortedNodes.values().stream()
                    .flatMap(this::computeAntiNodes)
                    .distinct()
                    .count();
        }

        protected java.util.Map<Character, List<Node>> getSortedNodes() {
            var spliterator =
                    Spliterators.spliterator(iterator(), (long) columnSize() * rowSize(), Spliterator.CONCURRENT);
            var sortedNodes = StreamSupport.stream(spliterator, true)
                    .filter(n -> n.getValue() != '.')
                    .collect(Collectors.groupingBy(Node::getValue, Collectors.toList()));
            return sortedNodes;
        }

        Stream<Coordinate> computeAntiNodes(List<Node> nodes) {
            if (nodes.size() < 2) {
                return Stream.of();
            }
            return Pair.getCombinations(nodes.stream().map(Node::getCoordinate).toList()).stream()
                    .flatMap(this::computeAntiNodes);
        }

        Stream<Coordinate> computeAntiNodes(Pair<Coordinate> coordinatePair) {
            Pair<Coordinate> antiNodePair = computeAssociatedAntiNodePair(coordinatePair);
            return Stream.of(antiNodePair.first(), antiNodePair.second()).filter(this::withinBounds);
        }

        protected Pair<Coordinate> computeAssociatedAntiNodePair(Pair<Coordinate> coordinatePair) {
            var row1 = coordinatePair.first().row();
            var col1 = coordinatePair.first().col();
            var row2 = coordinatePair.second().row();
            var col2 = coordinatePair.second().col();

            var antiNode1 = new Coordinate(2 * row1 - row2, 2 * col1 - col2);
            var antiNode2 = new Coordinate(2 * row2 - row1, 2 * col2 - col1);
            return new Pair<>(antiNode1, antiNode2);
        }
    }
}
