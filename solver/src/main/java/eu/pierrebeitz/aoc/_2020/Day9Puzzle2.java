package eu.pierrebeitz.aoc._2020;

import static eu.pierrebeitz.aoc.utils.AocUtils.loadInputForDay;

import java.io.IOException;

public class Day9Puzzle2 {

    public static void main(String[] args) throws IOException {
        try (var reader = loadInputForDay(2020, 9)) {
            System.out.println(
                    Day9Puzzle1.XmasSequence.fromStream(reader.lines()).findWeakness());
        }
    }
}
