package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8Puzzle1 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 8)) {
            var runtime = loadAndExecute(reader);
            System.out.println(runtime.getAccumulator());
        }
    }

    static Runtime loadAndExecute(BufferedReader reader) {
        var runtime = new Day8Puzzle1.Runtime(reader);
        while (runtime.hasNext()) {
            runtime.executeNext();
        }
        return runtime;
    }

    static class Runtime {
        private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(?<operation>nop|acc|jmp) (?<arg>[+|-]\\d+)");

        private final List<Instruction> instructions;
        private int accumulator;
        private int current;
        private boolean mutant;

        Runtime(BufferedReader reader) {
            instructions = reader.lines()
                  .map(INSTRUCTION_PATTERN::matcher)
                  .peek(Matcher::matches)
                  .map(mapper -> new Instruction(mapper.group("operation"), Integer.parseInt(mapper.group("arg"))))
                  .collect(Collectors.toList());
        }

        private Runtime(List<Instruction> instructions, int current, int accumulator) {
            this.instructions = new ArrayList<>(instructions);
            this.current = current;
            this.accumulator = accumulator;
            mutant = true;
        }

        boolean hasNext() {
            return current < instructions.size() && instructions.get(current) != null;
        }

        boolean isComplete() {
            return current == instructions.size();
        }

        void executeNext() {
            System.err.println(this);
            var currentInstruction = instructions.get(current);
            switch (currentInstruction.getOperation()) {
                case acc:
                    accumulator += currentInstruction.getArg();
                    instructions.set(current, null);
                    current++;
                    break;
                case jmp:
                    instructions.set(current, null);
                    current += currentInstruction.getArg();
                    break;
                case nop:
                    instructions.set(current, null);
                    current++;
                    break;
            }
        }

        Optional<Runtime> mutate() {
            var currentInstruction = instructions.get(current);
            if (Operation.acc == currentInstruction.getOperation() || mutant) {
                return Optional.empty();
            }
            var mutatedInstructions = new ArrayList<>(instructions);
            mutatedInstructions.set(current, currentInstruction.mutate());
            return Optional.of(new Runtime(mutatedInstructions, current, accumulator));
        }

        @Override
        public String toString() {
            return "Runtime{" +
                  "instructions=" + instructions +
                  ", accumulator=" + accumulator +
                  ", current=" + current +
                  '}';
        }

        int getAccumulator() {
            return accumulator;
        }
    }

    static class Instruction {
        private final Operation operation;
        private final int arg;

        private Instruction(String operation, int arg) {
            this.operation = Operation.valueOf(operation);
            this.arg = arg;
        }

        public Operation getOperation() {
            return operation;
        }

        public int getArg() {
            return arg;
        }

        public Instruction mutate() {
            var mutatedInstruction = operation == Operation.jmp ? Operation.nop : Operation.jmp;
            return new Instruction(mutatedInstruction.name(), arg);
        }

        @Override
        public String toString() {
            return "Instruction{" +
                  "operation=" + operation +
                  ", arg=" + arg +
                  '}';
        }
    }

    enum Operation {
        acc, jmp, nop
    }
}
