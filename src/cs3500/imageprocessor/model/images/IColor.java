package cs3500.imageprocessor.model.images;

/**
 * Represents a color with 3 8-bit channels. One for red, one for blue, and one for green.
 */
public interface IColor {

  /**
   * Returns the red value of this color.
   *
   * @return Color's red value.
   */
  int getRed();

  /**
   * Returns the green value of the color.
   *
   * @return Color's green value.
   */
  int getGreen();

  /**
   * Returns the blue value of the color.
   *
   * @return Color's blue value.
   */
  int getBlue();


}
