package cs3500.imageprocessor.model;

/**
 * Class representing a simple implementation of a pixel with a position and color.
 */
public class PixelImpl implements IPixel{
  private Position2D position;
  private IColor color;

  PixelImpl(Position2D position, IColor color) {
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
}
