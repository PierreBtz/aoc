package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1Puzzle1 implements DayPuzzle<Integer> {
    private static final String LIST_SEPARATOR = " {3}";

    @Override
    public Integer solve(BufferedReader reader) {
        var sortedLists = getSortedLists(reader);
        var distance = 0;
        for (var i = 0; i < sortedLists.first().size(); i++) {
            distance += Math.abs(sortedLists.first().get(i) - sortedLists.second().get(i));
        }
        return distance;
    }

    static Pair<List<Integer>> getSortedLists(BufferedReader reader) {
        var list1 = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();
        try {
            while (reader.ready()) {
                var split = reader.readLine().split(LIST_SEPARATOR);
                assert split.length == 2 : "not the expected split size";
                list1.add(Integer.parseInt(split[0].trim()));
                list2.add(Integer.parseInt(split[1].trim()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(list1);
        Collections.sort(list2);
        return new Pair<>(list1, list2);
    }
}
