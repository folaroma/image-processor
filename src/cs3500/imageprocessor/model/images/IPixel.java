package cs3500.imageprocessor.model.images;

/**
 * Interface to represent a pixel in an image. Pixels have a location in the image and a color.
 */
public interface IPixel {

  /**
   * Returns the position of the pixel.
   *
   * @return Pixel's position
   */
  Position2D getPosition();

  /**
   * Gets the RGB color of the pixel.
   *
   * @return The pixel's color.
   */
  IColor getColor();

}
