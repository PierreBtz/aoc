package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.stream.Collectors;

public class Day1Puzzle1 {
    private static final int TARGET = 2020;

    public static void main(String[] args) throws IOException {
        //let's go simple, given the number of inputs n2 is acceptable
        try (var reader = AocUtils.loadInputForDay(1)) {
            var expenses = reader.lines()
                  .map(Integer::valueOf)
                  .collect(Collectors.toList());

            var numberOne = Integer.MIN_VALUE;
            var numberTwo = Integer.MIN_VALUE;
            for (Integer value1 : expenses) {
                for (Integer value2 : expenses) {
                    if (value1 + value2 == TARGET) {
                        System.err.printf("Number 1 is %d, Number 2 is %d%n", value1, value2);
                        numberOne = value1;
                        numberTwo = value2;
                        break;
                    }
                    if (numberOne != Integer.MIN_VALUE) {
                        break;
                    }
                }
            }
            // puzzle is guaranteed to have a solution, no error handling
            System.out.println(numberOne * numberTwo);
        }
    }

}
