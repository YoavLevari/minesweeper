package org.example;

/**
 * Represents a single cell in the Minesweeper field.
 * <p>
 * A {@code Mine} object stores whether the cell contains a mine,
 * how many neighboring mines surround it, and whether the player
 * has already clicked this cell. This class is used by {@link Field2}
 * to build and manage the full game grid.
 * </p>
 */
public class Mine {

  /**
   * Indicates whether this cell contains an actual mine.
   * <p>
   * This value is final because a cell's mine status never changes
   * after the field is generated.
   * </p>
   */
  private final boolean explosion;

  /**
   * The number of adjacent mines surrounding this cell.
   * This value is assigned during field generation.
   */
  int number;

  /**
   * Tracks whether the player has clicked this cell.
   * Used to prevent double-counting and to control reveal logic.
   */
  boolean clicked;

  /**
   * Creates a new {@code Mine} cell.
   *
   * @param explosion {@code true} if this cell contains a mine,
   *                  {@code false} if it is a safe cell
   */
  public Mine(boolean explosion) {
    this.explosion = explosion;
  }

  /**
   * Returns whether this cell contains a mine.
   *
   * @return {@code true} if this cell is a mine, otherwise {@code false}
   */
  boolean getExplosion() {
    return explosion;
  }

  /**
   * Returns the number of neighboring mines around this cell.
   *
   * @return the count of adjacent mines
   */
  int getnumber() {
    return number;
  }
}
