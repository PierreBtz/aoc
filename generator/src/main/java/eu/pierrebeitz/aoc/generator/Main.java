package eu.pierrebeitz.aoc.generator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {
    private static final String YEAR_OPTION = "year";
    private static final String DAY_OPTION = "day";
    private static final String PART_OPTION = "part";
    private static final String OUTPUT_OPTION = "output";

    public static void main(String[] args) throws ParseException, IOException {
        var cmd = buildCommand(args);

        var year = cmd.<Integer>getParsedOptionValue(YEAR_OPTION);
        var day = cmd.<Integer>getParsedOptionValue(DAY_OPTION);
        var part = cmd.<Integer>getParsedOptionValue(PART_OPTION);
        var typeSpec = cmd.<Class<?>>getParsedOptionValue(OUTPUT_OPTION);

        new Generator(year, day, part, typeSpec).generatePuzzleFiles();
    }

    private static CommandLine buildCommand(String[] args) {
        var options = new Options();
        var year = new Option("y", YEAR_OPTION, true, "year of the puzzle");
        year.setType(Integer.class);
        year.setRequired(true);
        options.addOption(year);

        var day = new Option("d", DAY_OPTION, true, "day of the puzzle");
        day.setType(Integer.class);
        day.setRequired(true);
        options.addOption(day);

        var part = new Option("p", PART_OPTION, true, "part of the puzzle");
        part.setType(Integer.class);
        part.setRequired(true);
        options.addOption(part);

        var outputType = new Option("o", OUTPUT_OPTION, true, "output type of the puzzle");
        outputType.setType(Class.class);
        outputType.setRequired(true);
        options.addOption(outputType);

        var parser = new DefaultParser();
        var formatter = new HelpFormatter();
        try {
            return parser.parse(options, args);
        }catch (ParseException e) {
            formatter.printHelp("generator", options);
            System.exit(1);
        }
        // unreachable
        return null;
    }
}