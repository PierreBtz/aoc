package eu.pierrebeitz.aoc.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public enum AocUtils {
    ;

    public static BufferedReader loadInputForDay(int day) {
        return new BufferedReader(
              new InputStreamReader(
                    AocUtils.class.getResourceAsStream("/eu/pierrebeitz/aoc/_2020/input-day" + day + ".txt")
              )
        );
    }
}
