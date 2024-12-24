package eu.pierrebeitz.aoc.generator;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.ParameterSpec;
import com.palantir.javapoet.ParameterizedTypeName;
import com.palantir.javapoet.TypeSpec;
import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.TestData;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.Modifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;

public class Generator {
    public static final String AOC_GENERATOR_BASE_DIR = "aoc.generator.baseDir";
    private final int year;
    private final int day;
    private final int part;
    private final Class<?> outputType;

    Generator(int year, int day, int part, Class<?> outputType) {
        this.year = year;
        this.day = day;
        this.part = part;
        this.outputType = outputType;
    }

    void generatePuzzleFiles() throws IOException {
        generateProductionFile();
        generateTestFile();
        generateTestResources();
    }

    Path generateTestResources() throws IOException {
        var resourceDir =
                getBaseDirectory()
                        .resolve(
                                Path.of("solver", "src", "test", "resources", "eu", "pierrebeitz", "aoc", "_" + year
                                )
                        );
        var created = resourceDir.toFile().mkdirs();
        if (!created) {
            System.out.printf("Could not create the resource directory %s, maybe it already exits?%n", resourceDir);
        }

        Path resourceFile = resourceDir.resolve(String.format("input-example-day%d-puzzle%d.txt", day, part));
        created = resourceFile.toFile().createNewFile();
        if (!created) {
            System.out.printf("Could not create the resource file %s, maybe it already exists?%n", resourceFile);
        }
        return resourceFile;
    }

    Path generateTestFile() throws IOException {
        var typeBuilder = TypeSpec.classBuilder(generateClassName() + "Test");
        typeBuilder.addModifiers(Modifier.PUBLIC);

        var testMethodBuilder = MethodSpec.methodBuilder("testExample");
        testMethodBuilder.addAnnotation(Test.class);
        testMethodBuilder.addParameter(
                ParameterSpec.builder(BufferedReader.class, "reader")
                        .addAnnotation(TestData.class)
                        .build()
        );
        testMethodBuilder.addStatement("assertEquals(/*TODO*/, new $T().solve(reader))", ClassName.get(generatePackageName(), generateClassName()));
        typeBuilder.addMethod(testMethodBuilder.build());

        var generatedFilePath = JavaFile.builder(generatePackageName(), typeBuilder.build())
                .addStaticImport(org.junit.jupiter.api.Assertions.class, "assertEquals")
                .build()
                // strong assumption that we execute this from the root of the repo through the task file, KISS...
                .writeToPath(
                        getBaseDirectory()
                                .resolve(
                                        Path.of(
                                                "solver",
                                                "src",
                                                "test",
                                                "java"
                                        ))
                );

        System.out.printf("Generated test file at %s%n", generatedFilePath);
        return generatedFilePath;
    }

    Path generateProductionFile() throws IOException {
        var typeBuilder = TypeSpec.classBuilder(generateClassName());
        typeBuilder.addModifiers(Modifier.PUBLIC);
        typeBuilder.addSuperinterface(ParameterizedTypeName.get(DayPuzzle.class, outputType));

        var solveMethodBuilder = MethodSpec.methodBuilder("solve");
        solveMethodBuilder.addAnnotation(Override.class);
        solveMethodBuilder.addModifiers(Modifier.PUBLIC);
        solveMethodBuilder.returns(outputType);
        solveMethodBuilder.addParameter(BufferedReader.class, "reader");
        solveMethodBuilder.addComment("TODO: implement the solution");
        solveMethodBuilder.addStatement("return null");

        typeBuilder.addMethod(solveMethodBuilder.build());

        var type = typeBuilder.build();

        var generatedFilePath = JavaFile.builder(generatePackageName(), type)
                .build()
                // strong assumption that we execute this from the root of the repo through the task file, KISS...
                .writeToPath(getBaseDirectory()
                        .resolve(
                                Path.of(
                                        "solver",
                                        "src",
                                        "main",
                                        "java")
                        )
                );
        System.out.printf("Generated production file at %s%n", generatedFilePath);
        return generatedFilePath;
    }

    private String generatePackageName() {
        return String.format("eu.pierrebeitz.aoc._%d", year);
    }

    private String generateClassName() {
        return String.format(
                "Day%dPuzzle%d",
                day,
                part
        );
    }

    private Path getBaseDirectory() {
        var baseDir = System.getProperty(AOC_GENERATOR_BASE_DIR);
        if (baseDir == null) {
            return Path.of(System.getProperty("user.dir"));
        }
        return Path.of(baseDir);
    }
}
