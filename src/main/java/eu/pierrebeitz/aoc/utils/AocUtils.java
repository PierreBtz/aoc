package eu.pierrebeitz.aoc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public enum AocUtils {
    ;

    private static final String SEPARATOR = "";

    public static BufferedReader loadInputForDay(int day) {
        return new BufferedReader(
              new InputStreamReader(
                    AocUtils.class.getResourceAsStream("/eu/pierrebeitz/aoc/_2020/input-day" + day + ".txt")
              )
        );
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
}
