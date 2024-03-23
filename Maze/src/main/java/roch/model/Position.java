package roch.model;

public class Position {
    private final int row;
    private final int column;

    /**
     * Constructor of a position.
     * @param row The row of the position.
     * @param column The column of the position.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Copy constructor of Position.
     * @param pos the position to copy.
     */
    public Position(Position pos){
        this.row = pos.row;
        this.column = pos.column;
    }

    /**
     * Getter of x.
     * @return the x-axis of the position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter of y.
     * @return the y-axis of the position.
     */
    public int getColumn() {
        return column;
    }
    public Position next(Direction d){
        return new Position(this.row + d.getDeltaRow(),
                this.column + d.getDeltaColumn());
    }
}
