package eu.pierrebeitz.aoc._2023;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day2Puzzle1 implements DayPuzzle<Integer> {
    private static final Pattern GAME_ID_PATTERN = Pattern.compile("Game (?<id>\\d+)");
    private static final int MAX_RED_CUBES = 12;
    private static final int MAX_GREEN_CUBES = 13;
    private static final int MAX_BLUE_CUBES = 14;

    @Override
    public Integer solve(BufferedReader reader) {
        return reader.lines()
                .map(Game::fromString)
                .filter(Day2Puzzle1::isValid)
                .map(Game::id)
                .mapToInt(i -> i)
                .sum();
    }

    static boolean isValid(Game game) {
        boolean isValid = game.gameSets().stream().allMatch(Day2Puzzle1::isValid);
        System.err.printf("Game: %s is valid: %s%n", game, isValid);
        return isValid;
    }

    static boolean isValid(Game.GameSet set) {
        return set.redCount <= MAX_RED_CUBES && set.greenCount <= MAX_GREEN_CUBES && set.blueCount <= MAX_BLUE_CUBES;
    }

    record Game(int id, List<GameSet> gameSets) {

        static Game fromString(String str) {
            var gameSplit = str.split(":");
            assert gameSplit.length == 2 : "game input should only have two part";
            var gameIdMatcher = GAME_ID_PATTERN.matcher(gameSplit[0]);
            int gameId = Integer.MIN_VALUE;
            if (gameIdMatcher.find()) {
                gameId = Integer.parseInt(gameIdMatcher.group("id"));
            }
            System.err.printf("Found a game id: %d%n", gameId);
            var setSplit = gameSplit[1].split(";");
            var gameSets = Arrays.stream(setSplit).map(GameSet::fromString).toList();
            return new Game(gameId, gameSets);
        }

        record GameSet(int redCount, int greenCount, int blueCount) {
            private static final Pattern CUBE_COUNT_PATTERN =
                    Pattern.compile("^(?<count>\\d+) (?<color>red|green|blue)");

            static GameSet fromString(String str) {
                System.err.printf("Current set: %s%n", str);
                var cubeSplit = str.split(",");
                int redCount = 0;
                int greenCount = 0;
                int blueCount = 0;
                for (var cube : cubeSplit) {
                    var matcher = CUBE_COUNT_PATTERN.matcher(cube.trim());
                    if (matcher.find()) {
                        var count = Integer.parseInt(matcher.group("count"));
                        switch (matcher.group("color")) {
                            case "red":
                                redCount = count;
                                break;
                            case "green":
                                greenCount = count;
                                break;
                            case "blue":
                                blueCount = count;
                                break;
                        }
                    }
                }
                return new GameSet(redCount, greenCount, blueCount);
            }

            int power() {
                return redCount * greenCount * blueCount;
            }
        }
    }
}
