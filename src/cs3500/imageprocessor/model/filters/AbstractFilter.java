package cs3500.imageprocessor.model.filters;

import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to represent a filter with a given matrix. The matrix must have odd dimensions and
 * be square.
 */
public abstract class AbstractFilter implements IFilter {

  protected final double[][] matrix;

  /**
   * Creates and instance of the abstract filter with the given matrix.
   *
   * @param matrix Matrix to be associated with the filter.
   * @throws IllegalArgumentException If th matrix is null, it does not have odd dimensions, or it
   *                                  is not square.
   */
  protected AbstractFilter(double[][] matrix) throws IllegalArgumentException {
    if (matrix == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    if (matrix.length % 2 == 0) {
      throw new IllegalArgumentException("Dimensions of kernel must be odd.");
    }
    if (!this.isSquareMatrix(matrix)) {
      throw new IllegalArgumentException("Kernel must be square.");
    }
    this.matrix = matrix;
  }

  @Override
  public ImageInterface applyFilter(ImageInterface image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    List<ArrayList<IPixel>> filteredPixels = filtered(imagePixels, image);

    return new ImageImpl(filteredPixels);
  }

  /**
   * Returns the pixels of the image after the filter has been applied.
   *
   * @param pixels Pixels of the image to be filtered.
   * @param image  Image to be filtered.
   * @return The filtered pixels.
   */
  protected List<ArrayList<IPixel>> filtered(List<ArrayList<IPixel>> pixels,
      ImageInterface image) {
    List<ArrayList<IPixel>> filteredPixels = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < pixels.get(0).size(); j++) {
        row.add(filter(pixels.get(i).get(j), image));
      }
      filteredPixels.add(row);
    }

    return filteredPixels;

  }

  /**
   * Applies the filter to the given pixel using the filter's matrix. RGB values are clamped at a
   * max of 255 and a min of 0.
   *
   * @param pixel Pixel to be filtered.
   * @param image Original image.
   * @return The filtered pixel
   */
  protected IPixel filter(IPixel pixel, ImageInterface image) {
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    int pixelX = pixel.getPosition().getX();
    int pixelY = pixel.getPosition().getY();

    int neighborRange = matrix.length / 2;

    int red = 0;
    int green = 0;
    int blue = 0;

    for (int i = -1 * neighborRange; i <= neighborRange; i++) {
      for (int j = -1 * neighborRange; j <= neighborRange; j++) {
        try {
          double matrixValue = (double) Array
              .get(Array.get(matrix, i + neighborRange), j + neighborRange);

          int pixelR = imagePixels.get(pixelY + i).get(pixelX + j).getColor().getRed();
          int pixelG = imagePixels.get(pixelY + i).get(pixelX + j).getColor().getGreen();
          int pixelB = imagePixels.get(pixelY + i).get(pixelX + j).getColor().getBlue();

          pixelR *= matrixValue;
          pixelG *= matrixValue;
          pixelB *= matrixValue;

          red += pixelR;
          green += pixelG;
          blue += pixelB;

        } catch (IndexOutOfBoundsException ignored) {
          red += 0;
          green += 0;
          blue += 0;
        }
      }
    }

    red = clampValues(red);
    blue = clampValues(blue);
    green = clampValues(green);

    return new PixelImpl(new Position2D(pixelX, pixelY), new ColorImpl(red, green, blue));


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

  /**
   * Checks if the given 2d array matrix is a square matrix.
   * @param matrix Matrix to check.
   * @return True if square, false if not.
   */
  private boolean isSquareMatrix(double[][] matrix) {
    for(int i = 0; i < matrix.length; i++) {
        if (matrix[i].length != matrix.length) {
          return false;
      }
    }
    return true;
  }

}
