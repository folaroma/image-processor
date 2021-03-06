package cs3500.imageprocessor.model.images;

import java.util.Objects;
// Taken from starter code from the lectures.

/**
 * This class represents a 2D point represented in Cartesian coordinates.
 */
public class Position2D {

  private final int x;
  private final int y;

  /**
   * Create a 2D point given its x and y coordinates.
   *
   * @param x x-coordinate of the 2D point
   * @param y y-coordinate of the 2D point
   */
  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x-coordinate of this point.
   *
   * @return the x-coordinate as an integer
   */
  public int getX() {
    return this.x;
  }

  /**
   * Return the y-coordinate of this point.
   *
   * @return the y-coordinate as an integer.
   */
  public int getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Position2D)) {
      return false;
    }

    Position2D other = (Position2D) o;
    return this.x == other.x && this.y == other.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
