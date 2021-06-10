import cs3500.imageprocessor.model.colorTransformations.AbstractColorTransformation;

/**
 * Class that is a color transformation with a non 3 x 3 square matrix.
 */
public class NotThreeByThreeColorTransformation extends AbstractColorTransformation {

  /**
   * Creates an AbstractColorTransformation using a provided matrix.
   *
   * @param matrix Matrix to be applied to each pixel in an image.
   * @throws IllegalArgumentException If the supplied matrix is not 3 x 3 or is null.
   */
  protected NotThreeByThreeColorTransformation() throws IllegalArgumentException {
    super(new double[][]{{0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0}});
  }
}