package eu.pierrebeitz.aoc.utils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * a dumb matrix that converts the input into a matrix
 * and provides a few utils added when a puzzle needs it
 * each component of the matrix is wrapped into a node object lazily evaluated
 * allowing to simply add feature later
 */
public class Matrix implements Iterable<Matrix.Node> {
    private final List<List<Node>> matrix;

    public Matrix(BufferedReader reader) {
        matrix = reader.lines()
                .map(String::chars)
                .map(i -> i.mapToObj(c -> (char) c))
                .map(s -> s.map(Node::new))
                .map(Stream::toList)
                .toList();
        validateRows();
        fillNodes();
    }

    private void fillNodes() {
        var matrixIterator = new MatrixIterator();
        while (matrixIterator.hasNext()) {
            var currentRow = matrixIterator.getRow();
            var currentCol = matrixIterator.getCol();
            var currentNode = matrixIterator.next();
            currentNode.row = currentRow;
            currentNode.col = currentCol;
        }
    }

    public List<Node> getNeighbours(Node node) {
        var row = node.row;
        var col = node.col;

        var neighbourgs = new ArrayList<Node>();
        neighbourgs.add(getInDirection(row, col, Direction.NW));
        neighbourgs.add(getInDirection(row, col, Direction.N));
        neighbourgs.add(getInDirection(row, col, Direction.NE));
        neighbourgs.add(getInDirection(row, col, Direction.W));
        neighbourgs.add(getInDirection(row, col, Direction.E));
        neighbourgs.add(getInDirection(row, col, Direction.SW));
        neighbourgs.add(getInDirection(row, col, Direction.S));
        neighbourgs.add(getInDirection(row, col, Direction.SE));

        return neighbourgs.stream().filter(Objects::nonNull).toList();
    }

    public Node getAt(int row, int column) {
        return withinBounds(row, column) ? matrix.get(row).get(column) : null;
    }

    public Node getInDirection(int row, int column, Direction direction) {
        return getAt(row + direction.getDeltaRow(), column + direction.getDeltaColumn());
    }

    public boolean withinBounds(int row, int column) {
        return row < matrix.size() && column < matrix.get(0).size() && row >= 0 && column >= 0;
    }

    public boolean withinBounds(int row, int column, Direction direction) {
        return withinBounds(row + direction.getDeltaRow(), column + direction.getDeltaColumn());
    }

    public char getValueAt(int row, int column) {
        return getAt(row, column).value;
    }

    private void validateRows() {
        // let's reject an empty matrix
        var rowSize = matrix.size();
        if (rowSize == 0) {
            throw new UnsupportedOperationException("Matrix is empty");
        }

        // let's also reject matrix with different column sizes
        var rowSizes =
                matrix.stream().map(List::size).collect(Collectors.toSet()).size();
        if (rowSizes != 1) {
            throw new UnsupportedOperationException("Matrix only supports rows with the same sizes");
        }

        var columnSize = matrix.get(0).size();
        if (columnSize == 0) {
            throw new UnsupportedOperationException("Matrix is empty");
        }
    }

    @Override
    public MatrixIterator iterator() {
        return new MatrixIterator();
    }

    public int columnSize() {
        return matrix.get(0).size();
    }

    public class Node {
        private final char value;
        private int row;
        private int col;

        public Node(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value && row == node.row && col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, row, col);
        }
    }

    public class MatrixIterator implements Iterator<Node> {
        private int row;
        private int col;

        @Override
        public boolean hasNext() {
            return row < matrix.size() && col < columnSize();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            var value = getAt(row, col);
            movePointer();
            return value;
        }

        private void movePointer() {
            if (col < columnSize() - 1) {
                col++;
                return;
            }
            row++;
            col = 0;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    public enum Direction {
        N(-1, 0),
        NE(-1, 1),
        E(0, 1),
        SE(1, 1),
        S(1, 0),
        SW(1, -1),
        W(0, -1),
        NW(-1, -1);

        private final int deltaLine;
        private final int deltaColumn;

        Direction(int deltaRow, int deltaColumn) {
            this.deltaLine = deltaRow;
            this.deltaColumn = deltaColumn;
        }

        public int getDeltaRow() {
            return deltaLine;
        }

        public int getDeltaColumn() {
            return deltaColumn;
        }
    }
}
