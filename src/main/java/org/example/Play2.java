package org.example;

import java.util.Scanner;

/**
 * Handles the console-based interaction for playing Minesweeper.
 * <p>
 * This class manages all user input, initializes the game field,
 * processes player moves, and displays the board after each action.
 * It serves as the text‑based interface for the {@link Field2} model.
 * </p>
 */
public class Play2 {

  /**
   * Creates a new Play2 session and runs the full console game loop.
   * <p>
   * The constructor:
   * <ul>
   *     <li>Prompts the user for board dimensions and mine count</li>
   *     <li>Initializes a {@link Field2} object</li>
   *     <li>Handles the first click (which must be safe)</li>
   *     <li>Runs the main guessing loop until the game ends</li>
   * </ul>
   * </p>
   */
  public Play2() {

    Scanner scan = new Scanner(System.in);

    // --- Board setup ---
    System.out.println("How large do you want the board to be (enter the requested board length)?");
    String side = scan.next();

    System.out.println("Ok, now enter the requested board height:");
    String height = scan.next();

    System.out.println("Alright, how many mines do you want the field to have?");
    int mines = scan.nextInt();

    // Create the game field using the provided dimensions and mine count
    Field2 field = new Field2(Integer.parseInt(side), Integer.parseInt(height), mines);

    boolean gameOver = false;

    // Number of safe cells (used to determine win condition)
    int validMines = Integer.parseInt(side) * Integer.parseInt(height) - mines;

    // Print the initial empty field
    field.printField();

    // --- First click (must be safe) ---
    System.out.println("Please enter the vertical index:");
    int firstVerticalIndex = scan.nextInt();

    System.out.println("Please enter the horizontal index:");
    int firstHorizontalIndex = scan.nextInt();

    // Generate the field ensuring the first click is not a mine
    field.createField(new Position(firstVerticalIndex, firstHorizontalIndex));

    // Reveal the first chosen cell
    field.chooseMine(firstVerticalIndex, firstHorizontalIndex);
    field.printField();

    // --- Main game loop ---
    // Continues until the player hits a mine or reveals all safe cells
    while (!gameOver && field.minesGuessed != validMines) {

      System.out.println("What mine do you want to click?");
      System.out.println("What's the vertical index?");
      int verticalIndex = scan.nextInt();

      System.out.println("What's the horizontal index?");
      int horizontalIndex = scan.nextInt();

      // chooseMine returns true if the player hits a mine
      gameOver = field.chooseMine(verticalIndex, horizontalIndex);

      // Print updated board after each move
      field.printField();
    }

    // --- End of game ---
    if (field.minesGuessed == validMines) {
      System.out.println("You won! Congrats");
    } else {
      System.out.println("You hit a mine, better luck next time");
    }

    System.out.println("This is the field (X are the mines):");
    field.printMines();

    System.out.println("Thank you for playing");
  }
}

