package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;

import java.io.BufferedReader;

public class Day2Puzzle2 implements DayPuzzle<Integer> {

    @Override
    public Integer solve(BufferedReader reader) {
        return reader.lines()
                .map(Day2Puzzle1.Game::fromString)
                .map(this::getOptimalSet)
                .map(Day2Puzzle1.Game.GameSet::power)
                .mapToInt(i -> i)
                .sum();
    }

    Day2Puzzle1.Game.GameSet getOptimalSet(Day2Puzzle1.Game game) {
        var maxRed = 0;
        var maxGreen = 0;
        var maxBlue = 0;

        for (var set : game.gameSets()) {
            maxRed = Math.max(maxRed, set.redCount());
            maxGreen = Math.max(maxGreen, set.greenCount());
            maxBlue = Math.max(maxBlue, set.blueCount());
        }
        Day2Puzzle1.Game.GameSet result = new Day2Puzzle1.Game.GameSet(maxGreen, maxBlue, maxRed);
        System.err.printf("Game: %s, Optimal Set: %s%n", game, result);
        return result;
    }
}
