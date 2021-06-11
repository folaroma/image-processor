package cs3500.imageprocessor.model.colortransformations;

/**
 * Class to represent a grayscale transformation on an image.
 */
public class GrayscaleTransformation extends AbstractColorTransformation {

  /**
   * Creates a grayscale transformation with the matrix that will create the grayscale effect on a
   * pixel.
   */
  public GrayscaleTransformation() {
    super(new double[][]{{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}});
  }

}
