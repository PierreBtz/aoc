package eu.pierrebeitz.aoc.utils;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MatrixTest {

    @Test
    void parse() {
        var input = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
                """;

        var reader = new BufferedReader(new StringReader(input));
        var matrix = new Matrix(reader);
        assertEquals(10, matrix.columnSize());
        assertEquals('4', matrix.getValueAt(0, 0));
        assertEquals('#', matrix.getValueAt(3, 6));
    }

    @Test
    void iterator() {
        var input = """
                467..114..
                ...*......
                """;

        var reader = new BufferedReader(new StringReader(input));
        var matrix = new Matrix(reader);

        var output = StreamSupport.stream(matrix.spliterator(), false)
                .map(Matrix.Node::getValue)
                .map(Object::toString)
                .collect(Collectors.joining());

        assertEquals("467..114.....*......", output);
    }

    @Test
    void neighbourgs() {
        var input = """
                467..114..
                ...*......
                """;

        var reader = new BufferedReader(new StringReader(input));
        var matrix = new Matrix(reader);

        var node = matrix.getAt(1, 3);
        var neighbours = matrix.getNeighbours(node);
        assertEquals(5, neighbours.size());
    }

    @Test
    void inDirection() {
        var input = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
                """;

        var reader = new BufferedReader(new StringReader(input));
        var matrix = new Matrix(reader);

        assertEquals('6', matrix.getInDirection(0, 0, Matrix.Direction.E).getValue());
        assertEquals('.', matrix.getInDirection(0, 0, Matrix.Direction.S).getValue());
        assertNull(matrix.getInDirection(0, 0, Matrix.Direction.W));
    }

}