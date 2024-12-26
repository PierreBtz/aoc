package eu.pierrebeitz.aoc.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class AocTestExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.isAnnotated(TestData.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        var parameterType = parameterContext.getParameter().getType();
        if (!BufferedReader.class.equals(parameterType)) {
            throw new ExtensionConfigurationException("Extension only provides BufferedReader");
        }
        var testClass = extensionContext.getRequiredTestClass();
        var packagePattern =
                Pattern.compile("eu.pierrebeitz.aoc._(?<year>\\d\\d\\d\\d).Day(?<day>\\d)Puzzle(?<puzzle>\\d).*");
        var match = packagePattern.matcher(testClass.getName());
        if (match.matches()) {
            var year = match.group("year");
            var day = match.group("day");
            var puzzle = match.group("puzzle");
            var resourcePath =
                    String.format("/eu/pierrebeitz/aoc/_%s/input-example-day%s-puzzle%s.txt", year, day, puzzle);
            var is = AocUtils.class.getResourceAsStream(resourcePath);
            if (is != null) {
                return new BufferedReader(new InputStreamReader(is));
            }
            throw new ExtensionConfigurationException("Could not find the test resource, is it properly created?");
        }
        throw new ExtensionConfigurationException(
                "Could not find the year from the full class name, are the class name and package name correct?");
    }
}
