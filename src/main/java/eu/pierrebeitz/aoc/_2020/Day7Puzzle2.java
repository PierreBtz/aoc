package eu.pierrebeitz.aoc._2020;

import java.io.IOException;

import static eu.pierrebeitz.aoc._2020.Day7Puzzle1.BAG_OF_INTEREST;
import static eu.pierrebeitz.aoc._2020.Day7Puzzle1.buildBags;

public class Day7Puzzle2 {

    public static void main(String[] args) throws IOException {
        var bags = buildBags();
        var bagOfInterest = bags.get(BAG_OF_INTEREST);
        System.out.println(bagOfInterest.countNumberofPrimeBags(bags));
    }
}
