package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

  @Test
  void constructorStoresRowAndColCorrectly() {
    Position p = new Position(3, 7);

    assertEquals(3, p.row);
    assertEquals(7, p.col);
  }

  @Test
  void constructorThrowsOnNegativeRow() {
    assertThrows(IllegalArgumentException.class, () -> new Position(-1, 5));
  }

  @Test
  void constructorThrowsOnNegativeCol() {
    assertThrows(IllegalArgumentException.class, () -> new Position(5, -1));
  }

  @Test
  void constructorThrowsOnBothNegative() {
    assertThrows(IllegalArgumentException.class, () -> new Position(-3, -3));
  }

  @Test
  void positionFieldsAreMutableButShouldNotBeSetNegative() {
    Position p = new Position(2, 2);

    p.row = 10;
    p.col = 20;

    assertEquals(10, p.row);
    assertEquals(20, p.col);

    // You can decide if you want to forbid this too.
    // For now, we just confirm the class allows mutation.
  }

  @Test
  void twoPositionsWithSameValuesAreIndependent() {
    Position p1 = new Position(1, 1);
    Position p2 = new Position(1, 1);

    assertNotSame(p1, p2);
    assertEquals(p1.row, p2.row);
    assertEquals(p1.col, p2.col);
  }
}
