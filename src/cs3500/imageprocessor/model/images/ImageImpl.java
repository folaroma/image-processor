package cs3500.imageprocessor.model.images;

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
  public IPixel filter(IPixel pixel, double[][] matrix) {
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
          double matrixValue = (double)Array.get(Array.get(matrix, i + neighborRange), j + neighborRange);

          int pixelR = imagePixels.get(pixelY + i).get(pixelX + j).getColor().getRed();
          int pixelG = imagePixels.get(pixelY + i).get(pixelX + j).getColor().getGreen();
          int pixelB = imagePixels.get(pixelY + i).get(pixelX + j).getColor().getBlue();

          pixelR *= matrixValue;
          pixelG *= matrixValue;
          pixelB *= matrixValue;

          red += pixelR;
          green += pixelG;
          blue += pixelB;

        }
        catch (IndexOutOfBoundsException ignored) {
          red += 0;
          green += 0;
          blue += 0;
        }
      }
    }

    red = bounds(red);
    blue = bounds(blue);
    green = bounds(green);

    return new PixelImpl(new Position2D(pixelX, pixelY), new ColorImpl(red, green, blue));


  }

  @Override
  public IPixel colorTransform(IPixel pixel, double[][] matrix) {
    int red = pixel.getColor().getRed();
    int green = pixel.getColor().getGreen();
    int blue = pixel.getColor().getBlue();

    int positionX = pixel.getPosition().getX();
    int positionY = pixel.getPosition().getY();

    int alteredRed = 0;
    int alteredGreen = 0;
    int alteredBlue = 0;

    for (int j = 0; j < matrix[0].length; j++) {
      alteredRed += (int) (red * matrix[j][0]);
      alteredGreen += (int) (green * matrix[j][1]);
      alteredBlue += (int) (blue * matrix[j][2]);
    }

    return new PixelImpl(new Position2D(positionX, positionY), new ColorImpl(alteredRed,
        alteredGreen, alteredBlue));

  }

  private int bounds(int rgb) {
    if (rgb > 255) {
      return 255;
    }
    else if (rgb < 0) {
      return 0;
    }

    return rgb;

  }


}
