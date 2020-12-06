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

}