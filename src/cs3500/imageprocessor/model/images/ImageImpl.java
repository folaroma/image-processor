package cs3500.imageprocessor.model.images;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a simple implementation of an Image, which is a 2D array of pixels. This array
 * is represneted by a 2D ArrayList.
 */
public class ImageImpl implements ImageInterface {

  private final List<ArrayList<IPixel>> pixels;

  public ImageImpl(List<ArrayList<IPixel>> pixels) throws IllegalArgumentException {
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
  public IPixel filter(IPixel pixel, double[][] matrix) throws IllegalArgumentException {
    if (matrix.length % 2 == 0) {
      throw new IllegalArgumentException("Dimensions of kernel must be odd.");
    }
    if (matrix.length != matrix[0].length) {
      throw new IllegalArgumentException("Kernel must be square.");
    }
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(this.pixels);
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

  @Override
  public IPixel colorTransform(IPixel pixel, double[][] matrix) throws IllegalArgumentException {
    if (matrix[0].length != 3) {
      throw new IllegalArgumentException("Transformation matrix must be nX3.");
    }
    int red = pixel.getColor().getRed();
    int green = pixel.getColor().getGreen();
    int blue = pixel.getColor().getBlue();

    int positionX = pixel.getPosition().getX();
    int positionY = pixel.getPosition().getY();

    int alteredRed = (int) (red * matrix[0][0] + green * matrix[0][1] + blue * matrix[0][2]);
    int alteredGreen = (int) (red * matrix[1][0] + green * matrix[1][1] + blue * matrix[1][2]);
    int alteredBlue = (int) (red * matrix[2][0] + green * matrix[2][1] + blue * matrix[2][2]);

    alteredRed = clampValues(alteredRed);
    alteredGreen = clampValues(alteredGreen);
    alteredBlue = clampValues(alteredBlue);

    return new PixelImpl(new Position2D(positionX, positionY), new ColorImpl(alteredRed,
        alteredGreen, alteredBlue));

  }

  private int clampValues(int rgb) {
    if (rgb > 255) {
      return 255;
    } else if (rgb < 0) {
      return 0;
    }
    return rgb;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImageImpl)) {
      return false;
    }

    ImageImpl other = (ImageImpl) o;
    return this.pixels.equals(other.pixels);
  }

  @Override
  public int hashCode(){
    return Objects.hash(this.pixels);
  }


}
