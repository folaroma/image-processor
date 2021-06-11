package testingtransformations;

import cs3500.imageprocessor.model.colortransformations.AbstractColorTransformation;

/**
 * Transformation that can take color values over 255.
 */
public class OverLimitTransformation extends AbstractColorTransformation {

  /**
   * Creates an AbstractColorTransformation using a provided matrix.
   *
   * @throws IllegalArgumentException If the supplied matrix is not 3 x 3 or is null.
   */
  public OverLimitTransformation() throws IllegalArgumentException {
    super(new double[][]{{2, 2, 2},
        {2, 2, 2},
        {2, 2, 2}});
  }
}
