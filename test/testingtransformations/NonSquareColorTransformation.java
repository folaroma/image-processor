package testingtransformations;

import cs3500.imageprocessor.model.colortransformations.AbstractColorTransformation;

/**
 * Color transformation with an invalid index, that is not square.
 */
public class NonSquareColorTransformation extends AbstractColorTransformation {

  /**
   * Creates an AbstractColorTransformation using a provided matrix.
   *
   * @throws IllegalArgumentException If the supplied matrix is not 3 x 3 or is null.
   */
  public NonSquareColorTransformation() throws IllegalArgumentException {
    super(new double[][]{{0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0},
        {0.2126, 0.7152, 0.0722, 0}});
  }
}
