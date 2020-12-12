package eu.pierrebeitz.aoc.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static eu.pierrebeitz.aoc.utils.AocUtils.readByGroupAndMapToObject;

class AocUtilsTest {

    @ParameterizedTest
    @MethodSource("generateReadByGroupAndMapToObjectData")
    public void readByGroupAndMapToObjectTest(BufferedReader input, Function<String, String> mapper, List<String> output) throws IOException {
        var actual = readByGroupAndMapToObject(input, mapper);
        Assertions.assertEquals(output, actual);
    }

    static Stream<Arguments> generateReadByGroupAndMapToObjectData() {
        return Stream.of(
              Arguments.of(loadResource("readByGroupAndMapToObjectTest-1.txt"), (Function<String, String>) s -> s, Arrays.asList("abc\n", "def\n")),
              Arguments.of(loadResource("readByGroupAndMapToObjectTest-2.txt"), (Function<String, String>) s -> "toto" + s, Arrays.asList("totoabc\n", "totodef\n")),
              Arguments.of(loadResource("readByGroupAndMapToObjectTest-3.txt"), (Function<String, String>) s -> s, Arrays.asList("abc\ndef\n", "ghi\n"))
        );
    }

    private static BufferedReader loadResource(String resource) {
        return new BufferedReader(
              new InputStreamReader(AocUtilsTest.class.getResourceAsStream("/eu/pierrebeitz/aoc/utils/" + resource)));
    }

    @ParameterizedTest
    @MethodSource("generateCombinationsOfData")
    public void combinationsOf(List<Integer> input, List<List<Integer>> output) {
        Assertions.assertEquals(output, AocUtils.combinationsOf(input));
    }

    private static Stream<Arguments> generateCombinationsOfData() {
        return Stream.of(
              // test tests the exact output which we don't really care
              // proper thing would be to return tupples instead of lists but this is overkill for our problem
              Arguments.of(Arrays.asList(0, 1, 2, 3, 4), Arrays.asList(
                    Arrays.asList(3, 4),
                    Arrays.asList(2, 4),
                    Arrays.asList(2, 3),
                    Arrays.asList(1, 4),
                    Arrays.asList(1, 3),
                    Arrays.asList(1, 2),
                    Arrays.asList(0, 4),
                    Arrays.asList(0, 3),
                    Arrays.asList(0, 2),
                    Arrays.asList(0, 1)
              ))
        );
    }

}