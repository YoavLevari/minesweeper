package org.example;

import java.util.ArrayList;
import java.util.List;

public class Position {

    int row;

    int col;


    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public static void main (String[] args) {
        List<Integer> tester;
        tester = new ArrayList<Integer>();
        tester.add(1);
        tester.add(2);
        tester.add(3);
        tester.add(2);
        tester.add(5);
        tester.add(6);
        tester.add(7);
        System.out.println(tester);
        System.out.println("--------");
        System.out.println(tester.subList(0, 2));
    }
}
