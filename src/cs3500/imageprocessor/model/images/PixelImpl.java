package cs3500.imageprocessor.model.images;

import java.util.Objects;

/**
 * Class representing a simple implementation of a pixel with a position and color.
 */
public class PixelImpl implements IPixel {

  private final Position2D position;
  private final IColor color;

  public PixelImpl(Position2D position, IColor color) throws IllegalArgumentException {
    if (position == null || color == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    this.position = position;
    this.color = color;
  }

  @Override
  public Position2D getPosition() {
    return new Position2D(position.getX(), position.getY());
  }

  @Override
  public IColor getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PixelImpl)) {
      return false;
    }

    PixelImpl other = (PixelImpl) o;
    return this.position.equals(other.position) && this.color.equals(other.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, color);
  }

}
