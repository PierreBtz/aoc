package eu.pierrebeitz.aoc.utils;

import java.io.BufferedReader;

@FunctionalInterface
public interface DayPuzzle<T> {

    T solve(BufferedReader reader);
}
