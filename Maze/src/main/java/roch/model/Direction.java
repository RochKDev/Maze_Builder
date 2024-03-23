package roch.model;

/**
 * Represents a direction in the board.
 *
 * @author rochk
 */
public enum Direction {
    UP(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    DOWN(-1, 0);

    private final int deltaRow;
    private final int deltaColumn;

    /**
     * creates a Direction
     *
     * @param deltaRow the direction of the row
     * @param deltaColumn the direction of the column
     */
    Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;

    }

    /**
     * Gets current deltaRow
     *
     * @return current deltaRow
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Gets current deltaColumn
     *
     * @return current deltaColumn
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

}