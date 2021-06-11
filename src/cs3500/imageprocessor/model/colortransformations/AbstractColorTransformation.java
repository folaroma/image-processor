package cs3500.imageprocessor.model.colortransformations;

import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to represent a color transformation using a 3 x 3 matrix to apply to each pixel.
 */
public abstract class AbstractColorTransformation implements IColorTransformation {

  protected final double[][] matrix;

  /**
   * Creates an AbstractColorTransformation using a provided matrix.
   *
   * @param matrix Matrix to be applied to each pixel in an image.
   * @throws IllegalArgumentException If the supplied matrix is not 3 x 3 or is null.
   */
  protected AbstractColorTransformation(double[][] matrix) throws IllegalArgumentException {
    if (matrix == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    if (matrix.length != 3 || matrix[0].length != 3) {
      throw new IllegalArgumentException("Transformation matrix must be 3 x 3.");
    }
    this.matrix = matrix;
  }

  @Override
  public ImageInterface applyTransformation(ImageInterface image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    return new ImageImpl(transform(image, imagePixels));
  }

  /**
   * Applies the given transformation matrix to each pixel in the supplied image.
   *
   * @param image       Image to apply transformation to.
   * @param imagePixels Pixels of the image.
   * @return The updated pixels with the transformation applied.
   */
  protected List<ArrayList<IPixel>> transform(ImageInterface image,
      List<ArrayList<IPixel>> imagePixels) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(colorTransform(p));
      }
      updatedPixels.add(row);
    }
    return updatedPixels;
  }

  /**
   * Applies the given transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel  Pixel to transform.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {

    int red = pixel.getColor().getRed();
    int green = pixel.getColor().getGreen();
    int blue = pixel.getColor().getBlue();

    int positionX = pixel.getPosition().getX();
    int positionY = pixel.getPosition().getY();

    int alteredRed = (int) (red * this.matrix[0][0] + green * this.matrix[0][1] + blue * this.matrix[0][2]);
    int alteredGreen = (int) (red * this.matrix[1][0] + green * this.matrix[1][1] + blue * this.matrix[1][2]);
    int alteredBlue = (int) (red * this.matrix[2][0] + green * this.matrix[2][1] + blue * this.matrix[2][2]);

    alteredRed = clampValues(alteredRed);
    alteredGreen = clampValues(alteredGreen);
    alteredBlue = clampValues(alteredBlue);

    return new PixelImpl(new Position2D(positionX, positionY), new ColorImpl(alteredRed,
        alteredGreen, alteredBlue));

  }

  /**
   * Clamps the values of invalid rgb values if they exceed 255 or are less than 0. If it is in
   * range it stays the same
   *
   * @param rgb RGB value to clamp.
   * @return The clamped value.
   */
  private int clampValues(int rgb) {
    if (rgb > 255) {
      return 255;
    } else if (rgb < 0) {
      return 0;
    }
    return rgb;
  }

}
