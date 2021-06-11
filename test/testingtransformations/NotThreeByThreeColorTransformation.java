package testingtransformations;

import cs3500.imageprocessor.model.colortransformations.AbstractColorTransformation;

/**
 * Class that is a color transformation with a non 3 x 3 square matrix.
 */
public class NotThreeByThreeColorTransformation extends AbstractColorTransformation {

  /**
   * Creates an AbstractColorTransformation using a provided matrix.
   *
   * @throws IllegalArgumentException If the supplied matrix is not 3 x 3 or is null.
   */
  public NotThreeByThreeColorTransformation() throws IllegalArgumentException {
    super(new double[][]{{0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0}});
  }
}
