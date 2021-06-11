package testingtransformations;

import cs3500.imageprocessor.model.colortransformations.AbstractColorTransformation;

/**
 * Transformation that can take color values under 0.
 */
public class UnderLimitTransformation extends AbstractColorTransformation {


  /**
   * Creates an AbstractColorTransformation using a provided matrix.
   *
   * @throws IllegalArgumentException If the supplied matrix is not 3 x 3 or is null.
   */
  public UnderLimitTransformation() throws IllegalArgumentException {
    super(new double[][]{{-1, -1, -1},
        {-1, -1, -1},
        {-1, -1, -1}});
  }
}
