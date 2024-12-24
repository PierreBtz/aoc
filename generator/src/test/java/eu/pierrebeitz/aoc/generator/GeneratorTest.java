package eu.pierrebeitz.aoc.generator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneratorTest {
    private String oldBaseDir;

    @BeforeEach
    void setUp(@TempDir Path tempDir) {
        oldBaseDir = System.getProperty(Generator.AOC_GENERATOR_BASE_DIR);
        System.setProperty(Generator.AOC_GENERATOR_BASE_DIR, tempDir.toString());
    }

    @AfterEach
    void tearDown() {
        if (oldBaseDir == null) {
            System.clearProperty(Generator.AOC_GENERATOR_BASE_DIR);
        } else {
            System.setProperty(Generator.AOC_GENERATOR_BASE_DIR, oldBaseDir);
        }
    }

    @Test
    void generateProductionFile() throws IOException {
        var year = 1970;
        var day = 1;
        var part = 1;

        var generatedFile = new Generator(year, day, part, String.class).generateProductionFile();

        assertTrue(generatedFile.toFile().exists());

        try (var is = GeneratorTest.class.getResourceAsStream("/expected-production-file.java")) {
            assertEquals(
                    new String(is.readAllBytes(), StandardCharsets.UTF_8),
                    Files.readString(generatedFile, StandardCharsets.UTF_8)
            );
        }
    }

    @Test
    void generateTestFile() throws IOException {
        var year = 1970;
        var day = 1;
        var part = 1;

        var generatedFile = new Generator(year, day, part, String.class).generateTestFile();

        assertTrue(generatedFile.toFile().exists());

        try (var is = GeneratorTest.class.getResourceAsStream("/expected-test-file.java")) {
            assertEquals(
                    new String(is.readAllBytes(), StandardCharsets.UTF_8),
                    Files.readString(generatedFile, StandardCharsets.UTF_8)
            );
        }
    }

    @Test
    void generateTestResourceFile() throws IOException {
        var year = 1970;
        var day = 1;
        var part = 1;

        var generatedFile = new Generator(year, day, part, String.class).generateTestResources();

        assertTrue(generatedFile.toFile().exists());
    }
}