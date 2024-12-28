package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Pair;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day8Puzzle2 implements DayPuzzle<Long> {
    @Override
    public Long solve(BufferedReader reader) {
        var map = new MapPart2(reader);
        System.err.println(map);
        return map.computeAntiNodeSize();
    }

    static class MapPart2 extends Day8Puzzle1.Map {

        public MapPart2(BufferedReader reader) {
            super(reader);
        }

        public MapPart2(MapPart2 map) {
            super(map);
        }

        @Override
        long computeAntiNodeSize() {
            var sortedNodes = getSortedNodes();

            var antinodes = sortedNodes.values().stream()
                    .flatMap(this::computeAntiNodes)
                    .distinct()
                    .toList();

            var debugMap = new MapPart2(this);
            debugMap.markCoordinates(antinodes, '@');
            System.err.println(debugMap);

            return antinodes.size();
        }

        @Override
        Stream<Coordinate> computeAntiNodes(List<Node> nodes) {
            if (nodes.size() < 2) {
                return Stream.of();
            }
            return Pair.getCombinations(nodes.stream().map(Node::getCoordinate).toList()).stream()
                    .flatMap(this::computeAntiNodes);
        }

        @Override
        Stream<Coordinate> computeAntiNodes(Pair<Coordinate> coordinatePair) {
            var antiNodes = new HashSet<Coordinate>();
            computeAntiNodes(coordinatePair, antiNodes);
            return antiNodes.stream();
        }

        void computeAntiNodes(Pair<Coordinate> coordinatePair, Set<Coordinate> antiNodes) {
            if (!withinBounds(coordinatePair.first()) || !withinBounds(coordinatePair.second())) {
                return;
            }
            antiNodes.add(coordinatePair.first());
            antiNodes.add(coordinatePair.second());
            var antiNodePair = computeAssociatedAntiNodePair(coordinatePair);
            var firstAntiNodeIsNew = withinBounds(antiNodePair.first()) && antiNodes.add(antiNodePair.first());
            var secondAntiNodeIsNew = withinBounds(antiNodePair.second()) && antiNodes.add(antiNodePair.second());
            // find the proper pair
            var dist1 = coordinatePair.first().distance(antiNodePair.first());
            var dist2 = coordinatePair.first().distance(antiNodePair.second());
            if (dist1 < dist2) {
                if (firstAntiNodeIsNew) {
                    computeAntiNodes(new Pair<>(coordinatePair.first(), antiNodePair.first()), antiNodes);
                }
                if (secondAntiNodeIsNew) {
                    computeAntiNodes(new Pair<>(coordinatePair.second(), antiNodePair.second()), antiNodes);
                }
            } else {
                if (firstAntiNodeIsNew) {
                    computeAntiNodes(new Pair<>(coordinatePair.second(), antiNodePair.first()), antiNodes);
                }
                if (secondAntiNodeIsNew) {
                    computeAntiNodes(new Pair<>(coordinatePair.first(), antiNodePair.second()), antiNodes);
                }
            }
        }
    }
}
