package cs3500.imageprocessor.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a simple implementation of an Image, which is a 2D array of pixels. This array
 * is represneted by a 2D ArrayList.
 */
public class ImageImpl implements ImageInterface {

  private final List<ArrayList<IPixel>> pixels;

  public ImageImpl(List<ArrayList<IPixel>> pixels) throws IllegalArgumentException{
    if (pixels == null) {
      throw new IllegalArgumentException("List of pixels cannot be null.");
    }
    this.pixels = pixels;
  }

  @Override
  public List<ArrayList<IPixel>> getPixels() {
    return new ArrayList<>(pixels);
  }

  @Override
  public void filter(IPixel pixel, double[][] matrix) {
    List<List<IPixel>> imagePixels = new ArrayList<>(this.pixels);
    int pixelX = pixel.getPosition().getX();
    int pixelY = pixel.getPosition().getY();

    int neighborRange = matrix.length / 2;

    for (int i = -1 * neighborRange; i <= neighborRange; i++) {
      for (int j = -1 * neighborRange; j <= neighborRange; j++) {
        try {
          double matrixValue = (double)Array.get(Array.get(matrix, i + neighborRange), j + neighborRange);

          int red = imagePixels.get(pixelX + i).get(pixelY + j).getColor().getRed();
          int green = imagePixels.get(pixelX + i).get(pixelY + j).getColor().getGreen();
          int blue = imagePixels.get(pixelX + i).get(pixelY + j).getColor().getBlue();

          red *= matrixValue;
          green *= matrixValue;
          blue *= matrixValue;
        }
        catch (IndexOutOfBoundsException ie) {
          ie.printStackTrace();
        }
      }
    }

  }

}
