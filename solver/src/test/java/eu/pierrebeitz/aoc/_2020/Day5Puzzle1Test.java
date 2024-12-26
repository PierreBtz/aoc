package eu.pierrebeitz.aoc._2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.pierrebeitz.aoc._2020.Day5Puzzle1.Seat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Day5Puzzle1Test {

    @ParameterizedTest
    @CsvSource({"BFFFBBFRRR, 70, 7, 567", "FFFBBBFRRR, 14, 7, 119", "BBFFBBFRLL, 102, 4, 820"})
    public void parseSeat(String input, int row, int column, int id) {
        var seat = Seat.fromString(input);
        assertEquals(row, seat.getRow());
        assertEquals(column, seat.getColumn());
        assertEquals(id, seat.getId());
    }
}
