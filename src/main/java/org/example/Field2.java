package org.example;

import static java.lang.System.out;

import java.util.function.Supplier;

/**
 * Represents the full, finalized implementation of the Minesweeper game field.
 * <p>
 * This {@code Field2} class replaces the earlier experimental {@code Field}
 * version and contains the complete logic for generating the board, placing
 * mines, ensuring the first click is safe, counting neighboring mines, and
 * handling user interactions during gameplay.
 * </p>
 *
 * <p>
 * Unlike the original {@code Field} class, this implementation is stable,
 * feature‑complete, and used by {@link Play2} to run the actual game.
 * The older {@code Field} class remains in the project only as a record of
 * the initial design attempt.
 * </p>
 */
public class Field2 {

    private boolean created = false;

    private final int rows;
    private final int cols;
    final int mineAmount;
    int minesGuessed;
    Mine[][] playField;

    // Allows tests to control randomness
    private Supplier<Position> randomSupplier = this::randomPoint;

    /**
     * Creates a new Minesweeper field with the given dimensions and mine count.
     *
     * @param rows       number of rows in the grid
     * @param cols       number of columns in the grid
     * @param mineAmount total number of mines to place
     */
    public Field2(int rows, int cols, int mineAmount) {
        this.rows = rows;
        this.cols = cols;
        this.mineAmount = mineAmount;

        playField = new Mine[rows][cols];
    }

    /**
     * Generates the full minefield, ensuring the first user guess is always safe.
     * <p>
     * This method:
     * <ul>
     *     <li>Prevents regeneration if the field is already created</li>
     *     <li>Rejects impossible mine counts</li>
     *     <li>Randomly places mines, avoiding the first clicked cell</li>
     *     <li>Fills remaining cells with safe {@code Mine} objects</li>
     *     <li>Calculates the number of neighboring mines for each cell</li>
     * </ul>
     * </p>
     *
     * @param firstGuess the position the user clicks first, which must not contain a mine
     */
    void createField(Position firstGuess) {
        if (created) {
            return; // field already generated
        }

        if (mineAmount > rows * cols / 2) {
            System.out.println("too many mines");
            return;
        }

        int minesPlaced = 0;

        // Randomly place mines, avoiding the first clicked cell
        while (minesPlaced != mineAmount) {
            Position mine = randomSupplier.get();
            if ((mine.col != firstGuess.col || mine.row != firstGuess.row)
                && playField[mine.row][mine.col] == null) {

                playField[mine.row][mine.col] = new Mine(true);
                minesPlaced++;
            }
        }

        // Fill remaining cells with safe mines
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (playField[row][col] == null) {
                    playField[row][col] = new Mine(false);
                }
            }
        }

        // Count neighboring mines for each cell
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int minesAround = 0;

                // Check all 8 directions with boundary checks
                if (row != 0 && col != 0 && playField[row-1][col-1].getExplosion()) minesAround++; // top-left
                if (row != 0 && playField[row-1][col].getExplosion()) minesAround++;               // top
                if (row != 0 && col != cols-1 && playField[row-1][col+1].getExplosion()) minesAround++; // top-right
                if (col != 0 && playField[row][col-1].getExplosion()) minesAround++;              // left
                if (col != cols-1 && playField[row][col+1].getExplosion()) minesAround++;         // right
                if (row != rows-1 && col != 0 && playField[row+1][col-1].getExplosion()) minesAround++; // bottom-left
                if (row != rows-1 && playField[row+1][col].getExplosion()) minesAround++;         // bottom
                if (row != rows-1 && col != cols-1 && playField[row+1][col+1].getExplosion()) minesAround++; // bottom-right

                playField[row][col].number = minesAround;
            }
        }

        created = true;
    }

    /**
     * Generates a random valid position within the field.
     *
     * @return a randomly selected {@code Position} inside the grid
     */
    Position randomPoint() {
        int randomRow = (int) (Math.random() * rows);
        int randomCol = (int) (Math.random() * cols);
        return new Position(randomRow, randomCol);
    }

    /**
     * Processes a user click on the given cell.
     * <p>
     * This method:
     * <ul>
     *     <li>Validates that the field exists</li>
     *     <li>Checks bounds and repeated clicks</li>
     *     <li>Reveals the cell</li>
     *     <li>Returns whether the user hit a mine</li>
     * </ul>
     * </p>
     *
     * @param row the row index clicked
     * @param col the column index clicked
     * @return {@code true} if the user hit a mine (game over), otherwise {@code false}
     */
    boolean chooseMine(int row, int col) {
        if (!created) {
            System.out.println("there is no field, too many mines");
            return true;
        }

        if (row >= rows || col >= cols) {
            out.println("guess is out of bounds");
            return false;
        }

        if (playField[row][col].clicked) {
            out.println("location is already clicked");
            return false;
        }

        if (playField[row][col].getExplosion()) {
            out.println("you hit a mine, you LOOSE!");
            return true;
        }

        playField[row][col].clicked = true;
        minesGuessed++;
        return false;
    }

    /**
     * Prints the current visible state of the field.
     * <p>
     * Revealed cells show their mine count; unrevealed cells show {@code X}.
     * </p>
     */
    void printField() {
        out.print("   ");
        for (int i = 0; i < rows; i++) {
            out.print(i + " ");
        }
        out.println();

        out.print("  ");
        for (int i = 0; i < rows; i++) {
            out.print("__");
        }
        out.println();

        for (int row = 0; row < rows; row++) {
            out.print(row + " |");
            for (int col = 0; col < cols; col++) {
                if (created && playField[row][col].clicked) {
                    out.print(playField[row][col].number + " ");
                } else {
                    out.print("X ");
                }
            }
            out.println();
        }
    }

    /**
     * Prints the full mine layout for debugging or end‑of‑game display.
     * <p>
     * Mines are shown as {@code X}, safe cells as {@code 0}.
     * </p>
     */
    void printMines() {
        if (!created) {
            return;
        }

        out.print("   ");
        for (int i = 0; i < rows; i++) {
            out.print(i + " ");
        }
        out.println();

        out.print("  ");
        for (int i = 0; i < rows; i++) {
            out.print("__");
        }
        out.println();

        for (int row = 0; row < rows; row++) {
            out.print(row + " |");
            for (int col = 0; col < cols; col++) {
                if (playField[row][col].getExplosion()) {
                    out.print("X ");
                } else {
                    out.print("0 ");
                }
            }
            out.println();
        }
    }

    // Used only for testing to inject predictable mine positions
    void setRandomSupplier(Supplier<Position> supplier) {
        this.randomSupplier = supplier;
    }
}
