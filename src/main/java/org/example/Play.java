package org.example;

import java.util.Scanner;

/**
 * Represents the original, first-generation console interface for the Minesweeper game.
 * <p>
 * This {@code Play} class was created during the initial attempt at building the
 * user interaction layer. It contains the early structure for prompting the user,
 * reading moves, and driving the game loop, but it lacks the refined logic,
 * safety checks, and improved flow found in the updated implementation.
 * </p>
 *
 * <p><strong>Note:</strong> This class has been replaced by the newer and more
 * complete {@link Play2} version, which provides a cleaner setup process,
 * improved input handling, and a more robust connection to the finalized
 * {@link Field2} game model. The original {@code Play} class remains in the
 * project for reference and to document the evolution of the design.</p>
 */
public class Play {

    public Play() {
        Scanner scan = new Scanner(System.in);
        System.out.println("how large do you want the board to be(enter the requested side length)");
        String side = scan.next();

        System.out.println(" alright, how many mines do you want the field to have");

        int mines = scan.nextInt();

        Field field = new Field(Integer.parseInt(side),mines);

        boolean gameOver = false;

        int validMines = Integer.parseInt(side) * Integer.parseInt(side) - mines;

        field.printField();

        while(!gameOver && field.minesGuessed != validMines){
            System.out.println("what mine do you want to click");
            System.out.println("what's the vertical index?");
            int verticalIndex = scan.nextInt();
            System.out.println("what's the horizontal index?");
            int horizontalIndex = scan.nextInt();
            gameOver = field.chooseMine(verticalIndex,horizontalIndex);
            field.printField();
        }

        if (field.minesGuessed == validMines){
            System.out.println("You won! Congrats");
        }
        else{System.out.println("You lost! You LOOSER10!, u suck, L + ratio");}

        System.out.println("this is the field (X are the mines)");
        field.printMines();

        System.out.println("Thank you for playing line sleeper");
    }


    public static void main(String[] args) {
        new Play();
    }

}
