package cs3500.imageprocessor.model.filters;

import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilter implements IFilter {
  protected final double[][] matrix;

  protected AbstractFilter(double[][] matrix) {
    this.matrix = matrix;
  }

  public abstract ImageInterface applyFilter(ImageInterface image) throws IllegalArgumentException;

  protected List<ArrayList<IPixel>> filtered(List<ArrayList<IPixel>> pixels, double[][] matrix,
      ImageInterface image) throws IllegalArgumentException{
    List<ArrayList<IPixel>> filteredPixels = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < pixels.get(0).size(); j++) {
        row.add(filter(pixels.get(i).get(j), matrix, image));
      }
      filteredPixels.add(row);
    }

    return filteredPixels;

  }

  protected IPixel filter(IPixel pixel, double[][] matrix, ImageInterface image)
      throws IllegalArgumentException {
    if (matrix.length % 2 == 0) {
      throw new IllegalArgumentException("Dimensions of kernel must be odd.");
    }
    if (matrix.length != matrix[0].length) {
      throw new IllegalArgumentException("Kernel must be square.");
    }
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

  private int clampValues(int rgb) {
    if (rgb > 255) {
      return 255;
    } else if (rgb < 0) {
      return 0;
    }
    return rgb;
  }

}
