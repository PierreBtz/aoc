package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.stream.Collectors;

public class Day1Puzzle2 {
    private static final int TARGET = 2020;

    public static void main(String[] args) throws IOException {
        //let's go simple, given the number of inputs n3 is acceptable
        try (var reader = AocUtils.loadInputForDay(2020, 1)) {
            var expenses = reader.lines()
                  .map(Integer::valueOf)
                  .collect(Collectors.toList());

            var numberOne = Integer.MIN_VALUE;
            var numberTwo = Integer.MIN_VALUE;
            var numberThree = Integer.MIN_VALUE;
            for (Integer value1 : expenses) {
                for (Integer value2 : expenses) {
                    for (Integer value3 : expenses) {
                        if (value1 + value2 + value3 == TARGET) {
                            System.err.printf("Number 1 is %d, Number 2 is %d, Number 3 is %d%n", value1, value2, value3);
                            numberOne = value1;
                            numberTwo = value2;
                            numberThree = value3;
                            break;
                        }
                        if (numberOne != Integer.MIN_VALUE) {
                            break;
                        }
                    }
                }
            }
            // puzzle is guaranteed to have a solution, no error handling
            System.out.println(numberOne * numberTwo * numberThree);
        }
    }

}
