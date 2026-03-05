package org.example;

import java.util.Arrays;

import static java.lang.Math.random;

public class Main {
    public static void main(String[] args) {

        int[][] matrix = new int[3][3];
        System.out.println("Hello, World!");
        int randomNum = (int) (Math.random() * 10);
        int test = 12;
        matrix[0][0] = randomNum;
        System.out.println(randomNum);
    }
}