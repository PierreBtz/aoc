package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;

public class Day2Puzzle2 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2)) {
            var result = reader.lines()
                  .map(RecordPuzzle2::new)
                  .filter(RecordPuzzle2::isValid)
                  .count();
            System.out.println(result);
        }
    }

    static class RecordPuzzle2 extends Day2Puzzle1.ValidableRecord {

        RecordPuzzle2(String record) {
            super(record);
        }

        boolean isValid() {
            char char1 = password.charAt(range.getLower() - 1);
            char char2 = password.charAt(range.getUpper() - 1);
            if (char1 == char2) {
                return false;
            }
            return char1 == constraint || char2 == constraint;
        }
    }
}
