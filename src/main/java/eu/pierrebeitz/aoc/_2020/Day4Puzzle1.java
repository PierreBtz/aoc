package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Day4Puzzle1 {
    private static final String SEPARATOR = "";

    public static void main(String[] args) throws IOException {
        computeForValidator(new PasswordValidatorPuzzle1());
    }

    static void computeForValidator(Predicate<Password> passwordValidator) throws IOException {
        try (var reader = AocUtils.loadInputForDay(4)) {
            var count = buildPasswords(reader).stream()
                  .filter(passwordValidator)
                  .peek(System.err::println)
                  .count();
            System.out.println(count);
        }
    }

    private static List<Password> buildPasswords(java.io.BufferedReader reader) throws IOException {
        var acc = "";
        var result = new ArrayList<Password>();
        while (reader.ready()) {
            var currentLine = reader.readLine();
            if (SEPARATOR.equals(currentLine)) {
                result.add(Password.fromString(acc));
                acc = "";
            } else {
                acc += currentLine + "\n";
            }
        }
        // do not forget the last line...
        result.add(Password.fromString(acc));
        return result;
    }

    static class Password {
        private static final Pattern PATTERN = Pattern.compile("pid:(?<pid>\\S+)|byr:(?<byr>\\S+)|hgt:(?<hgt>\\S+)|iyr:(?<iyr>\\S+)|hcl:(?<hcl>\\S+)|eyr:(?<eyr>\\S+)|ecl:(?<ecl>\\S+)|cid:(?<cid>\\S+)");

        final String ecl;
        final String pid;
        final String eyr;
        final String hcl;
        final String byr;
        final String iyr;
        final String cid;
        final String hgt;

        Password(String ecl, String pid, String eyr, String hcl, String byr, String iyr, String cid, String hgt) {
            this.ecl = ecl;
            this.pid = pid;
            this.eyr = eyr;
            this.hcl = hcl;
            this.byr = byr;
            this.iyr = iyr;
            this.cid = cid;
            this.hgt = hgt;
        }

        static Password fromString(String str) {
            var matcher = PATTERN.matcher(str);
            var parsingResults = new HashMap<String, String>() {{
                put("ecl", null);
                put("pid", null);
                put("eyr", null);
                put("hcl", null);
                put("byr", null);
                put("iyr", null);
                put("cid", null);
                put("hgt", null);
            }};
            while (matcher.find()) {
                parsingResults.forEach((k, v) -> {
                          if (v == null) {
                              try {
                                  parsingResults.put(k, matcher.group(k));
                              } catch (IllegalArgumentException e) {
                                  // do nothing
                              }
                          }
                      }
                );

            }
            return new
                  Password(
                  parsingResults.get("ecl"),
                  parsingResults.get("pid"),
                  parsingResults.get("eyr"),
                  parsingResults.get("hcl"),
                  parsingResults.get("byr"),
                  parsingResults.get("iyr"),
                  parsingResults.get("cid"),
                  parsingResults.get("hgt")
            );
        }

        @Override
        public String toString() {
            return "Password{" +
                  "ecl='" + ecl + '\'' +
                  ", pid='" + pid + '\'' +
                  ", eyr='" + eyr + '\'' +
                  ", hcl='" + hcl + '\'' +
                  ", byr='" + byr + '\'' +
                  ", iyr='" + iyr + '\'' +
                  ", cid='" + cid + '\'' +
                  ", hgt='" + hgt + '\'' +
                  '}';
        }
    }

    private static class PasswordValidatorPuzzle1 implements Predicate<Password> {

        @Override
        public boolean test(Password password) {
            return password.ecl != null
                  && password.pid != null
                  && password.eyr != null
                  && password.hcl != null
                  && password.byr != null
                  && password.iyr != null
                  && password.hgt != null;
        }
    }
}
