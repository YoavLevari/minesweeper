package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MineTest {

  @Test
  void mineStoresExplosionFlagCorrectly() {
    Mine mine1 = new Mine(true);
    Mine mine2 = new Mine(false);

    assertTrue(mine1.getExplosion());
    assertFalse(mine2.getExplosion());
  }

  @Test
  void mineStartsUnclicked() {
    Mine mine = new Mine(false);
    assertFalse(mine.clicked);
  }

  @Test
  void mineStartsWithZeroNumber() {
    Mine mine = new Mine(false);
    assertEquals(0, mine.number);
  }

  @Test
  void mineNumberCanBeUpdated() {
    Mine mine = new Mine(false);
    mine.number = 3;
    assertEquals(3, mine.number);
  }

  @Test
  void mineClickedCanBeUpdated() {
    Mine mine = new Mine(false);
    mine.clicked = true;
    assertTrue(mine.clicked);
  }
}
