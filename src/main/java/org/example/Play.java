package org.example;

import java.util.Scanner;

public class Play {

    public Play() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to line sleeper");
        System.out.println("due to copyright this is different than mine sweeper");
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
