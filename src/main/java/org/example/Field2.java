package org.example;

import static java.lang.System.out;

public class Field2 {

    private boolean created = false;

    private final int rows;

    private final int cols;

    final int mineAmount;

    int minesGuessed;

    Mine[][] playField;

    public Field2(int rows, int cols, int mineAmount) {
        this.rows = rows;
        this.cols = cols;
        this.mineAmount = mineAmount;

        playField = new Mine[rows][cols];

    }

    void createField(Position firstGuess) {
        if (created) {
            return;
        }
        if (mineAmount > rows * cols /2) {
            System.out.println("too many mines");
            return;
        }
        int minesplaced = 0;

        while (minesplaced != mineAmount) {
            Position mine = randomPoint();
            if ((mine.col != firstGuess.col || mine.row != firstGuess.row) && playField[mine.row][mine.col] == null) {
                playField[mine.row][mine.col] = new Mine(true);
                minesplaced++;
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (playField[row][col] == null) {
                    playField[row][col] = new Mine(false);
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int minesAround = 0;
                //top left
                if (row != 0 && col != 0 && playField[row-1][col-1].getexplosion()) {
                    minesAround ++;
                }
                //top
                if (row != 0 && playField[row-1][col].getexplosion()) {
                    minesAround ++;
                }
                //top right
                if (row != 0 && col != cols-1 && playField[row-1][col+1].getexplosion()) {
                    minesAround ++;
                }
                //left
                if (col != 0 && playField[row][col-1].getexplosion()) {
                    minesAround ++;
                }
                //right
                if (col != cols-1 && playField[row][col+1].getexplosion()) {
                    minesAround ++;
                }
                //bottom left
                if (row != rows-1 && col != 0 && playField[row+1][col-1].getexplosion()) {
                    minesAround ++;
                }
                //bottom
                if (row != rows-1 && playField[row+1][col].getexplosion()) {
                    minesAround ++;
                }
                //bottom right
                if (row != rows-1 && col != cols-1 && playField[row+1][col+1].getexplosion()) {
                    minesAround ++;
                }
                playField[row][col].number = minesAround;
            }
        }
        created = true;
    }

    Position randomPoint(){
        int randomRow = (int) (Math.random() * rows);

        int randomCol = (int) (Math.random() * cols);

        return new Position(randomRow,randomCol);
    }

    boolean chooseMine(int row, int col){
        if (!created){
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
        if (playField[row][col].getexplosion()) {
            out.println("you hit a mine, you LOOSE!");
            return true;
        }
        else {
            playField[row][col].clicked = true;
            minesGuessed ++;
            return false;
        }
    }

    void printField(){
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
                }
                else{
                    out.print("X" + " ");
                }
            }
            out.println();
        }
    }

    void printMines(){
        if (!created){
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
                if (playField[row][col].getexplosion()) {
                    out.print("X ");
                }
                else {
                    out.print("O ");
                }
            }
            out.println();
        }
    }

    public static void main(String[] args) {

        Field2 field = new Field2(10,10,10);

        field.printField();

        System.out.println("here");
        field.createField(new Position(0,0));

        field.printField();

        System.out.println("tests:");

        field.chooseMine(0, 0);
        field.chooseMine(1, 1);
        field.chooseMine(5, 5);
        field.chooseMine(9, 9);

        field.printField();
        field.printMines();
    }

}
