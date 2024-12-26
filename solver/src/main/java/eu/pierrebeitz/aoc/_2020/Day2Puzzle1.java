package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;
import java.io.IOException;
import java.util.regex.Pattern;

public class Day2Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 2)) {
            var result = reader.lines()
                    .map(RecordPuzzle1::new)
                    .filter(RecordPuzzle1::isValid)
                    .count();
            System.out.println(result);
        }
    }

    static class RecordPuzzle1 extends ValidableRecord {

        RecordPuzzle1(String record) {
            super(record);
        }

        boolean isValid() {
            var count = password.chars().filter(c -> c == constraint).count();
            var valid = range.contains(count);
            System.err.println(this + "\n is: " + (valid ? "valid" : "not valid"));
            return valid;
        }
    }

    abstract static class ValidableRecord {
        final Range range;
        final char constraint;
        final String password;

        ValidableRecord(String record) {
            var pattern = Pattern.compile("^(\\d+)-(\\d+) (\\D): (.+)");
            var matcher = pattern.matcher(record);
            matcher.matches();
            assert matcher.groupCount() == 4 : "This record needs to be analyzed: " + record;
            range = new Range(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            assert matcher.group(3).length() == 1 : "This record needs to be analyzed: " + record;
            constraint = matcher.group(3).charAt(0);
            password = matcher.group(4);
        }

        abstract boolean isValid();

        @Override
        public String toString() {
            return "ValidableRecord{" + "range="
                    + range + ", constraint="
                    + constraint + ", password='"
                    + password + '\'' + '}';
        }

        static class Range {
            private final int lower;
            private final int upper;

            Range(int lower, int upper) {
                this.lower = lower;
                this.upper = upper;
            }

            boolean contains(long number) {
                return number >= lower && number <= upper;
            }

            public int getLower() {
                return lower;
            }

            public int getUpper() {
                return upper;
            }

            @Override
            public String toString() {
                return "Range{" + "lower=" + lower + ", upper=" + upper + '}';
            }
        }
    }
}
