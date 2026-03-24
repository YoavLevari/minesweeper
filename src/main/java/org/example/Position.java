package org.example;

/**
 * Represents a coordinate on the Minesweeper board.
 * <p>
 * A {@code Position} stores a row and column index used to identify a specific
 * cell within the game grid. It is used throughout the model to reference
 * locations for mine placement, user selections, and neighbor calculations.
 * </p>
 */
public class Position {
    int row;
    int col;

    /**
     * Creates a new {@code Position} with the specified row and column.
     * row and col must be non-negative
     *
     * @param row the vertical index of the cell on the board
     * @param col the horizontal index of the cell on the board
     */
    public Position(int row, int col) {
        if (row < 0 || col < 0){
            throw new IllegalArgumentException();
        }
        this.row = row;
        this.col = col;
    }
}
