package cs3500.imageprocessor.model.colorTransformations;

import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractColorTransformation implements IColorTransformation {

  public abstract ImageInterface applyTransformation(ImageInterface image);

  protected List<ArrayList<IPixel>> transform(ImageInterface image,
      List<ArrayList<IPixel>> imagePixels, double[][] matrix) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();

    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(colorTransform(p, matrix));
      }
      updatedPixels.add(row);
    }

    return updatedPixels;


  }

  protected IPixel colorTransform(IPixel pixel, double[][] matrix) throws IllegalArgumentException {
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

}
