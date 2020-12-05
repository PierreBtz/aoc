package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc._2020.Day4Puzzle1.Password;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Day4Puzzle2 {

    public static void main(String[] args) throws IOException {
        Day4Puzzle1.computeForValidator(new PasswordValidatorPuzzle2());
    }

    private static class PasswordValidatorPuzzle2 implements Predicate<Password> {
        private static final String HGT_UNIT_CM = "cm";
        private static final String HGT_UNIT_IN = "in";
        private static final Pattern HGT_PATTERN = Pattern.compile("(\\d+)(" + HGT_UNIT_CM + "|" + HGT_UNIT_IN + ")");
        private static final Pattern HCL_PATTERN = Pattern.compile("#[a-f0-9]{6}");
        private static final Pattern ECL_PATTERN = Pattern.compile("(amb|blu|brn|gry|grn|hzl|oth)");
        private static final Pattern PID_PATTERN = Pattern.compile("\\d{9}");

        @Override
        public boolean test(Password password) {
            return validateByr(password)
                  && validateIyr(password)
                  && validateEyr(password)
                  && validateHgt(password)
                  && validateHcl(password)
                  && validateEcl(password)
                  && validatePid(password);
        }

        private boolean validateByr(Password password) {
            var byr = password.byr;
            if (byr != null) {
                try {
                    int i = Integer.parseInt(byr);
                    return i >= 1920 && i <= 2002;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return false;
        }

        private boolean validateIyr(Password password) {
            var iyr = password.iyr;
            if (iyr != null) {
                try {
                    int i = Integer.parseInt(iyr);
                    return i >= 2010 && i <= 2020;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return false;
        }

        private boolean validateEyr(Password password) {
            var eyr = password.eyr;
            if (eyr != null) {
                try {
                    int i = Integer.parseInt(eyr);
                    return i >= 2020 && i <= 2030;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return false;
        }

        private boolean validateHgt(Password password) {
            var hgt = password.hgt;
            if (hgt != null) {
                var matcher = HGT_PATTERN.matcher(hgt);
                if (matcher.matches()) {
                    var hgtValue = matcher.group(1);
                    var hgtUnit = matcher.group(2);
                    try {
                        int i = Integer.parseInt(hgtValue);
                        switch (hgtUnit) {
                            case HGT_UNIT_CM:
                                return i >= 150 && i <= 193;
                            case HGT_UNIT_IN:
                                return i >= 59 && i <= 76;
                            default:
                                throw new UnsupportedOperationException("Unexpected Unit " + hgtUnit);
                        }
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
            return false;
        }

        private boolean validateHcl(Password password) {
            var hcl = password.hcl;
            if (hcl != null) {
                var matcher = HCL_PATTERN.matcher(hcl);
                return matcher.matches();
            }
            return false;
        }

        private boolean validateEcl(Password password) {
            var ecl = password.ecl;
            if (ecl != null) {
                var matcher = ECL_PATTERN.matcher(ecl);
                return matcher.matches();
            }
            return false;
        }

        private boolean validatePid(Password password) {
            var pid = password.pid;
            if (pid != null) {
                var matcher = PID_PATTERN.matcher(pid);
                return matcher.matches();
            }
            return false;
        }
    }
}
