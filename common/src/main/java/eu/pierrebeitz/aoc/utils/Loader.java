package eu.pierrebeitz.aoc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Loader {
    private static final Path DATA_PATH = Path.of(".data");

    private final String year;
    private final String day;

    private final String puzzle;

    Loader(String year, String day, String puzzle) {
        this.year = year;
        this.day = day;
        this.puzzle = puzzle;
    }

    @SuppressWarnings("rawtypes")
    public void load()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
                    IOException, InterruptedException, ClassNotFoundException {
        String className = String.format("eu.pierrebeitz.aoc._%s.Day%sPuzzle%s", year, day, puzzle);
        var clasz = Loader.class.getClassLoader().loadClass(className);
        if (!DayPuzzle.class.isAssignableFrom(clasz)) {
            System.err.printf("%s does not implement %s%n", className, DayPuzzle.class);
        }
        var constructor = clasz.getDeclaredConstructor();
        var dayPuzzle = (DayPuzzle) constructor.newInstance();
        try (var br = loadInputForDay()) {
            System.out.println(dayPuzzle.solve(br));
        }
    }

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
                    IllegalAccessException, IOException, InterruptedException {
        if (args.length != 3) {
            System.err.println(
                    """
                    Not the proper argument count, should be:
                    * arg0: year
                    * arg1: day
                    * arg2: puzzle
                    """);
        }
        var year = args[0];
        var day = args[1];
        var puzzle = args[2];
        var loader = new Loader(year, day, puzzle);
        loader.load();
    }

    BufferedReader loadInputForDay() throws IOException, InterruptedException {
        if (!dataExistForDay()) {
            retrieveDataForDay();
        }
        return Files.newBufferedReader(getPathForDay(), StandardCharsets.UTF_8);
    }

    private void retrieveDataForDay() throws IOException, InterruptedException {
        var sessionId = System.getenv().get("AOC_SESSION_ID");
        Files.createDirectories(getDirectoryForDay());

        var req = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://adventofcode.com/%s/day/%s/input", year, day)))
                .header("Cookie", String.format("session=%s", sessionId))
                .build();

        try (var client = HttpClient.newBuilder().build()) {
            var resp = client.send(req, HttpResponse.BodyHandlers.ofInputStream());
            try (var body = resp.body()) {
                Files.write(getPathForDay(), body.readAllBytes());
            }
        }
    }

    private Path getDirectoryForDay() {
        return DATA_PATH.resolve(year);
    }

    private Path getPathForDay() {
        return getDirectoryForDay().resolve(day);
    }

    private boolean dataExistForDay() {
        return Files.exists(getPathForDay());
    }
}
