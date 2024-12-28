package eu.pierrebeitz.aoc.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;

class MatrixTest {

    @Test
    void parse() {
        var input =
                """
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
        var input =
                """
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

    @Test
    void print() {
        var input =
                """
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

        assertEquals(input, matrix.toString());
    }

    @Test
    void markCoordinates() {
        var input =
                """
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

        matrix.markCoordinates(List.of(new Matrix.Coordinate(0, 0), new Matrix.Coordinate(1, 1)), 'X');

        var output =
                """
                X67..114..
                .X.*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
                """;

        assertEquals(output, matrix.toString());
    }

    @Test
    void coordinateDistance() {
        var c1 = new Matrix.Coordinate(1, 1);
        var c2 = new Matrix.Coordinate(3, 3);

        assertEquals(2.83, c1.distance(c2), 0.01);
        assertEquals(2.83, c2.distance(c1), 0.01);
    }
}
