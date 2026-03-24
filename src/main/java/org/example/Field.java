package org.example;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * Represents the first experimental implementation of the Minesweeper board.
 * <p>
 * This class was created during the initial attempt at building the game logic.
 * It stores the board size, the number of mines, and the 2D array of {@link Mine}
 * objects that make up the playing field.
 * </p>
 *
 * <p><strong>Important:</strong> This version of the field is incomplete.
 * The full, polished, and actively used implementation is located in
 * {@code Field2}, which contains the final game logic, mine placement rules,
 * neighbor counting, and click handling.</p>
 *
 * <p>
 * This class remains in the project for reference and documentation purposes,
 * showing the evolution of the design before the final architecture was created.
 * </p>
 *
 * @author Yoav
 */
public class Field {

    private int side;

    private final int mineAmount;

    int minesGuessed;

    Mine[][] playField;

    public Field(int side, int mineAmount) {
        this.side = side;
        this.mineAmount = mineAmount-1;

        playField = new Mine[side][side];

        creatField();
    }


    public void creatField() {
        if (mineAmount > side*side){
            out.println("too many mines");
            return;
        }

        int remainder = mineAmount%side;


        //makes the field with mines
        for (int row = 0; row < side; row++) {
            int minesPerRow = mineAmount/side;
            if (row <= remainder) {
                minesPerRow++;
            }
            List<Integer> randomIndex = chooseRandom(minesPerRow);
            for (int col = 0; col < side; col++) {
                if(randomIndex.contains(col)) {
                    playField[row][col] = new Mine(true);
                }
                else{
                    playField[row][col] = new Mine(false);
                }
            }
        }

        //gives the mines numbers

        //for row = 0
        for(int col = 0; col < side; col++){
            int minesAround= 0;
            if (col != 0 && col != side - 1 && !playField[0][col].getExplosion()){
                if (playField[0][col-1].getExplosion()){
                    minesAround++;
                }
                if (playField[0][col+1].getExplosion()){
                    minesAround++;
                }
                if (playField[1][col-1].getExplosion()){
                    minesAround++;
                }
                if (playField[1][col].getExplosion()){
                    minesAround++;
                }
                if (playField[1][col + 1].getExplosion()){
                    minesAround++;
                }
            } else if (col == 0 && !playField[0][col].getExplosion()){
                if (playField[0][col+1].getExplosion()){
                    minesAround++;
                }
                if (playField[1][col].getExplosion()){
                    minesAround++;
                }
                if (playField[1][col + 1].getExplosion()){
                    minesAround++;
                }
            } else if (col == side-1 && !playField[0][col].getExplosion()) {
                if (playField[0][col-1].getExplosion()){
                    minesAround++;
                }
                if (playField[1][col-1].getExplosion()){
                    minesAround++;
                }
                if (playField[1][col].getExplosion()){
                    minesAround++;
                }
            }
            playField[0][col].number = minesAround;
        }

        // for row = side - 1
        for (int col = 0; col < side; col++) {
            int minesAround= 0;
            if (col != 0 && col != side - 1 && !playField[side - 1][col].getExplosion()){
                if (playField[side - 2][col - 1].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 2][col].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 2][col + 1].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 1][col-1].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 1][col+1].getExplosion()){
                    minesAround++;
                }
            } else if (col == 0 && !playField[side - 1][col].getExplosion()){
                if (playField[side - 2][col].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 2][col + 1].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 1][col+1].getExplosion()){
                    minesAround++;
                }
            } else if (col == side-1 && !playField[side - 1][col].getExplosion()) {
                if (playField[side - 2][col - 1].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 2][col].getExplosion()){
                    minesAround++;
                }
                if (playField[side - 1][col-1].getExplosion()){
                    minesAround++;
                }
            }
            playField[side - 1][col].number = minesAround;
        }

        //for the rest if the rows
        for (int row = 1; row < side-1; row++) {
            for (int col = 0; col < side; col++) {
                int minesAround = 0;
                if (col != 0 && col != side - 1 && !playField[row][col].getExplosion()){
                    if (playField[row - 1][col - 1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row - 1][col].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row - 1][col + 1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row][col-1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row][col+1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row+1][col-1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row + 1][col].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row + 1][col + 1].getExplosion()){
                        minesAround++;
                    }
                } else if (col == 0 && !playField[row][col].getExplosion()){
                    if (playField[row - 1][col].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row - 1][col + 1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row][col+1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row + 1][col].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row + 1][col + 1].getExplosion()){
                        minesAround++;
                    }
                } else if (col == side-1 && !playField[row][col].getExplosion()) {
                    if (playField[row - 1][col - 1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row - 1][col].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row][col-1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row+1][col-1].getExplosion()){
                        minesAround++;
                    }
                    if (playField[row + 1][col].getExplosion()){
                        minesAround++;
                    }
                }
                playField[row][col].number = minesAround;
            }
        }



    }

    List<Integer> chooseRandom(int mineAmount){
        
        List<Integer> output = new ArrayList<Integer>();
        
        List<Integer> numbers = new ArrayList<Integer>();
        for (int row = 0; row < side; row++) {
            numbers.add(row);
        }
        int arraySize = numbers.size();
        for (int num = 0; num < mineAmount; num++) {
            int randomMine = numbers.remove((int) (Math.random() * (arraySize - num)));
            output.add(randomMine);
        }
        return output;
    }


    void printField(){
        out.print("   ");
        for (int i = 0; i < side; i++) {
            out.print(i + " ");
        }
        out.println();
        out.print("  ");
        for (int i = 0; i < side; i++) {
            out.print("__");
        }
        out.println();
        for (int row = 0; row < side; row++) {
            out.print(row + " |");
            for (int col = 0; col < side; col++) {
                if (playField[row][col].clicked) {
                    out.print(playField[row][col].number + " ");
                }
                else if (!playField[row][col].clicked) out.print("X" + " ");
            }
            out.println();
        }
    }


    boolean chooseMine(int row, int col){
        if (row >= side || col >= side) {
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
        else {
            playField[row][col].clicked = true;
            minesGuessed ++;
            return false;
        }
    }

    void printMines(){
        out.print("   ");
        for (int i = 0; i < side; i++) {
            out.print(i + " ");
        }
        out.println();
        out.print("  ");
        for (int i = 0; i < side; i++) {
            out.print("__");
        }
        out.println();
        for (int row = 0; row < side; row++) {
            out.print(row + " |");
            for (int col = 0; col < side; col++) {
                if (playField[row][col].getExplosion()) {
                    out.print("X ");
                }
                else {
                    out.print("O ");
                }
            }
            out.println();
        }
    }
}
