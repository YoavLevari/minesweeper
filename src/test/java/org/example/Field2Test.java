package org.example;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Field2Test {

  @Test
  void fieldInitializesCorrectly() {
    Field2 field = new Field2(5, 7, 10);
    assertEquals(5, field.playField.length);
    assertEquals(7, field.playField[0].length);
    assertEquals(10, field.mineAmount);
  }

  @Test
  void firstClickIsAlwaysSafe() {
    Field2 field = new Field2(5, 5, 5);

    // Deterministic mine placement
    Queue<Position> mines = new ArrayDeque<>();
    mines.add(new Position(0, 0));
    mines.add(new Position(0, 1));
    mines.add(new Position(1, 0));
    mines.add(new Position(4, 4));
    mines.add(new Position(3, 3));

    field.setRandomSupplier(mines::remove);

    Position first = new Position(2, 2);
    field.createField(first);

    assertFalse(field.playField[2][2].getExplosion());
  }

  @Test
  void mineCountMatchesRequestedAmount() {
    Field2 field = new Field2(5, 5, 3);

    Queue<Position> mines = new ArrayDeque<>();
    mines.add(new Position(0, 0));
    mines.add(new Position(1, 1));
    mines.add(new Position(2, 2));

    field.setRandomSupplier(mines::remove);

    field.createField(new Position(4, 4));

    int count = 0;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        if (field.playField[r][c].getExplosion()) count++;
      }
    }

    assertEquals(3, count);
  }

  @Test
  void neighborCountsAreCorrect() {
    Field2 field = new Field2(3, 3, 1);

    // Place a single mine at (1,1)
    Queue<Position> mines = new ArrayDeque<>();
    mines.add(new Position(1, 1));

    field.setRandomSupplier(mines::remove);

    field.createField(new Position(0, 0));

    // All surrounding cells should have 1 neighbor
    assertEquals(1, field.playField[0][0].number);
    assertEquals(1, field.playField[0][1].number);
    assertEquals(1, field.playField[0][2].number);
    assertEquals(1, field.playField[1][0].number);
    assertEquals(1, field.playField[1][2].number);
    assertEquals(1, field.playField[2][0].number);
    assertEquals(1, field.playField[2][1].number);
    assertEquals(1, field.playField[2][2].number);
  }

  @Test
  void clickingMineReturnsTrue() {
    Field2 field = new Field2(3, 3, 1);

    Queue<Position> mines = new ArrayDeque<>();
    mines.add(new Position(0, 0));

    field.setRandomSupplier(mines::remove);

    field.createField(new Position(2, 2));

    assertTrue(field.chooseMine(0, 0));
  }

  @Test
  void clickingSafeCellReturnsFalse() {
    Field2 field = new Field2(3, 3, 1);

    Queue<Position> mines = new ArrayDeque<>();
    mines.add(new Position(0, 0));

    field.setRandomSupplier(mines::remove);

    field.createField(new Position(2, 2));

    assertFalse(field.chooseMine(2, 2));
  }

  @Test
  void clickingOutOfBoundsIsHandled() {
    Field2 field = new Field2(3, 3, 1);

    Queue<Position> mines = new ArrayDeque<>();
    mines.add(new Position(0, 0));

    field.setRandomSupplier(mines::remove);

    field.createField(new Position(1, 1));

    assertFalse(field.chooseMine(10, 10));
  }

  @Test
  void repeatedClickIsHandled() {
    Field2 field = new Field2(3, 3, 1);

    Queue<Position> mines = new ArrayDeque<>();
    mines.add(new Position(0, 0));

    field.setRandomSupplier(mines::remove);

    field.createField(new Position(1, 1));

    assertFalse(field.chooseMine(1, 1)); // first click
    assertFalse(field.chooseMine(1, 1)); // repeated click
  }
}
