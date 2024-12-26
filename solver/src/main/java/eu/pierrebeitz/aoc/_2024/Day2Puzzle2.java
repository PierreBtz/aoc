package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day2Puzzle2 implements DayPuzzle<Long> {
    @Override
    public Long solve(BufferedReader reader) {
        return reader.lines()
                .map(Day2Puzzle1.Report::fromInput)
                .map(Day2Puzzle2.PossibleReports::new)
                .filter(PossibleReports::isValid)
                .count();
    }

    static class PossibleReports {
        private final List<Day2Puzzle1.Report> reports;

        PossibleReports(Day2Puzzle1.Report report) {
            // this is brute force, but given the size of the reports, it should
            // be enough to get a solution in a reasonable time
            reports = new ArrayList<>();
            reports.add(report);
            var reportLevels = report.getLevels().stream().toList();
            for (var i = 0; i < reportLevels.size(); i++) {
                var copy = new ArrayList<Day2Puzzle1.Level>(reportLevels.size());
                copy.addAll(reportLevels);
                copy.remove(i);
                reports.add(new Day2Puzzle1.Report(new LinkedList<>(copy)));
            }
        }

        boolean isValid() {
            return reports.stream().parallel().anyMatch(Day2Puzzle1.Report::isValid);
        }
    }
}
