package org.example;

import java.util.Scanner;

public class Play2 {
  public Play2() {
    Scanner scan = new Scanner(System.in);
    System.out.println("how large do you want the board to be(enter the requested board length)");
    String side = scan.next();
    System.out.println("ok interesting, now enter the requested board hight");
    String hight = scan.next();

    System.out.println("alright, how many mines do you want the field to have");

    int mines = scan.nextInt();

    Field2 field = new Field2(Integer.parseInt(side),Integer.parseInt(hight),mines);

    boolean gameOver = false;

    int validMines = Integer.parseInt(side) * Integer.parseInt(hight) - mines;

    field.printField();

    System.out.println("please, enter the vertical index");

    int firstVerticalIndex = scan.nextInt();

    System.out.println("please, enter the horizontal index");

    int firstHorizontalIndex = scan.nextInt();

    field.createField(new Position(firstVerticalIndex,firstHorizontalIndex));

    field.chooseMine(firstVerticalIndex,firstHorizontalIndex);

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
    else{System.out.println("You hit a mine, Better luck next Time");}

    System.out.println("this is the field (X are the mines)");
    field.printMines();

    System.out.println("Thank you for playing");
  }

}
