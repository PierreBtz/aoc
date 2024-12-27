package eu.pierrebeitz.aoc._2024;

import eu.pierrebeitz.aoc.utils.DayPuzzle;
import eu.pierrebeitz.aoc.utils.Matrix;
import java.io.BufferedReader;

public class Day6Puzzle1 implements DayPuzzle<Integer> {
    @Override
    public Integer solve(BufferedReader reader) {
        var map = new Map(reader);
        System.err.printf("Parsed map:%n%s%n", map);

        return map.startMoving();
    }

    static class Map extends Matrix {
        static final char OBSTACLE = '#';
        static final char PATH_MARKER = 'X';
        private int guardPositionRow;
        private int guardPositionColumn;
        private Heading guardHeading;
        // markers for part 2
        static final char GOING_NORTH = 'N';
        static final char GOING_EAST = 'E';
        static final char GOING_SOUTH = 'S';
        static final char GOING_WEST = 'W';

        public Map(BufferedReader reader) {
            super(reader);
            initializeGuardPosition();
        }

        public Map(Map map) {
            super(map);
            guardHeading = map.guardHeading;
            guardPositionRow = map.guardPositionRow;
            guardPositionColumn = map.guardPositionColumn;
        }

        private void initializeGuardPosition() {
            var it = iterator();
            while (it.hasNext()) {
                var node = it.next();
                if (node.getValue() == Heading.N.symbol) {
                    guardPositionRow = node.getRow();
                    guardPositionColumn = node.getCol();
                    guardHeading = Heading.N;
                    return;
                }
            }
        }

        public boolean isMovingMarkerPath(char c) {
            return c == GOING_EAST || c == GOING_NORTH || c == GOING_SOUTH || c == GOING_WEST;
        }

        public char getMovingMarkerForCurrentHeading() {
            return switch (guardHeading) {
                case N -> GOING_NORTH;
                case E -> GOING_EAST;
                case S -> GOING_SOUTH;
                case W -> GOING_WEST;
            };
        }

        public Heading getHeadingFromMovingMarker() {
            return switch (getAt(guardPositionRow, guardPositionColumn).getValue()) {
                case GOING_NORTH -> Heading.N;
                case GOING_EAST -> Heading.E;
                case GOING_SOUTH -> Heading.S;
                case GOING_WEST -> Heading.W;
                default -> throw new IllegalArgumentException("Not a moving marker");
            };
        }

        public boolean startMovingAndDetectCycle() {
            var lastTurnWasUTurn = false;
            while (withinBounds(guardPositionRow, guardPositionColumn)) {
                var previousNode = getAt(guardPositionRow, guardPositionColumn);
                if (!isMovingMarkerPath(previousNode.getValue())) {
                    previousNode.setValue(getMovingMarkerForCurrentHeading());
                } else {
                    // we already passed, was it with the same direction?
                    if (getHeadingFromMovingMarker() == guardHeading) {
                        // cycle detected
                        return true;
                    }
                    // update the heading, note that we won't detect a cycle right away
                    // in case we are at a cross in the cycle (like in an 8 pattern)
                    // but that's ok, we'll detect it at the next iteration
                    previousNode.setValue(getMovingMarkerForCurrentHeading());
                }
                var newNode = getInDirection(guardPositionRow, guardPositionColumn, guardHeading.direction);
                if (newNode == null) {
                    break;
                }
                if (newNode.getValue() == OBSTACLE) {
                    guardHeading = guardHeading.nextHeading();
                    // peek into the next direction to see if we need to perform a U turn
                    var peekNode = getInDirection(guardPositionRow, guardPositionColumn, guardHeading.direction);
                    if (peekNode.getValue() == OBSTACLE) {
                        if (lastTurnWasUTurn) {
                            return true;
                        }
                        guardHeading = guardHeading.nextHeading();
                        lastTurnWasUTurn = true;
                    } else {
                        lastTurnWasUTurn = false;
                    }
                    continue;
                }
                guardPositionRow = newNode.getRow();
                guardPositionColumn = newNode.getCol();
            }
            return false;
        }

        public int startMoving() {
            var positionCount = 0;
            while (withinBounds(guardPositionRow, guardPositionColumn)) {
                var previousNode = getAt(guardPositionRow, guardPositionColumn);
                if (previousNode.getValue() != PATH_MARKER) {
                    positionCount++;
                    previousNode.setValue(PATH_MARKER);
                }
                System.err.printf("Guard is at %d %d%n", guardPositionRow, guardPositionColumn);

                var newNode = getInDirection(guardPositionRow, guardPositionColumn, guardHeading.direction);
                if (newNode == null) {
                    break;
                }
                if (newNode.getValue() == OBSTACLE) {
                    guardHeading = guardHeading.nextHeading();
                    continue;
                }

                guardPositionRow = newNode.getRow();
                guardPositionColumn = newNode.getCol();
            }
            return positionCount;
        }

        enum Heading {
            N(Direction.N, '^', Direction.E),
            E(Direction.E, '>', Direction.S),
            S(Direction.S, 'v', Direction.W),
            W(Direction.W, '<', Direction.N);

            private final Direction direction;
            private final char symbol;
            private final Direction nextDirection;

            Heading(Direction direction, char symbol, Direction nextDirection) {
                this.direction = direction;
                this.symbol = symbol;
                this.nextDirection = nextDirection;
            }

            public Heading nextHeading() {
                return fromDirection(nextDirection);
            }

            static Heading fromDirection(Direction direction) {
                switch (direction) {
                    case N:
                        return N;
                    case E:
                        return E;
                    case S:
                        return S;
                    case W:
                        return W;
                    default:
                        throw new IllegalArgumentException("No mapping for this direction");
                }
            }
        }
    }
}
