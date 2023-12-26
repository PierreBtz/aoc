package eu.pierrebeitz.aoc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public enum AocUtils {
    ;

    private static final String SEPARATOR = "";

    // use the Loader class
    @Deprecated
    public static BufferedReader loadInputForDay(int year, int day) {
        var resourcePath = "/eu/pierrebeitz/aoc/_" + year + "/input-" + (isJunit() ? "example-" : "") + "day" + day + ".txt";
        return new BufferedReader(
              new InputStreamReader(
                    AocUtils.class.getResourceAsStream(resourcePath)
              )
        );
    }

    private static boolean isJunit() {
        return Arrays.stream(Thread.currentThread().getStackTrace())
              .anyMatch(e -> e.getClassName().startsWith("org.junit"));
    }

    public static <T> List<T> readByGroupAndMapToObject(BufferedReader reader, Function<String, T> mapper) throws IOException {
        var acc = "";
        var result = new ArrayList<T>();
        while (reader.ready()) {
            var currentLine = reader.readLine();
            if (SEPARATOR.equals(currentLine)) {
                result.add(mapper.apply(acc));
                acc = "";
            } else {
                acc += currentLine + "\n";
            }
        }
        // do not forget the last line...
        result.add(mapper.apply(acc));
        return result;
    }

    public static <T> List<List<T>> combinationsOf(List<T> list) {
        List<List<T>> combinations = new ArrayList<>();
        combination(combinations, new ArrayList<>(), list, 0, list.size() - 1);
        return combinations;
    }

    // simple version for 2 elements but support for k can easily added if a future puzzle needs it
    private static <T> void combination(List<List<T>> combinations, List<T> currentCombination, List<T> source, int start, int end) {
        if (2 == currentCombination.size()) {
            combinations.add(currentCombination);
        } else if (start <= end) {
            combination(combinations, new ArrayList<>(currentCombination), source, start + 1, end);
            currentCombination.addAll(Collections.singletonList(source.get(start)));
            combination(combinations, currentCombination, source, start + 1, end);
        }
    }
}
