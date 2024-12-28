package eu.pierrebeitz.aoc.utils;

import java.util.ArrayList;
import java.util.List;

public record Pair<T>(T first, T second) {

    public static <T> List<Pair<T>> getCombinations(List<T> elements) {
        var combinations = new ArrayList<Pair<T>>();
        for (var i = 0; i < elements.size(); i++) {
            for (var j = i; j < elements.size(); j++) {
                if (i != j) {
                    combinations.add(new Pair<>(elements.get(i), elements.get(j)));
                }
            }
        }
        return combinations;
    }
}
