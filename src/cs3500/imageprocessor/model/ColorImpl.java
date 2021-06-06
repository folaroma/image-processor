package cs3500.imageprocessor.model;

/**
 * Represents a simple implementation of a color, which has 3 8-bit channels for red, green, and
 * blue values as ints. Values go from 0-255.
 */
public class ColorImpl implements IColor{
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Creates and instance of a color with RGB values.
   * @param red Red value for the color.
   * @param green Green value for the color.
   * @param blue Blue value for the color.
   * @throws IllegalArgumentException If the color value provided in not in the range of 0-255.
   */
  ColorImpl(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Color value must be in range of 0-255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  @Override
  public int getRed() {
    return red;
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public int getBlue() {
    return blue;
  }
}
