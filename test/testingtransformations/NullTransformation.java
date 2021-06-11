package testingtransformations;

import cs3500.imageprocessor.model.colortransformations.AbstractColorTransformation;

/**
 * Bad transformation with null matrix.
 */
public class NullTransformation extends AbstractColorTransformation {

  /**
   * Creates an AbstractColorTransformation using a provided matrix.
   *
   * @throws IllegalArgumentException If the supplied matrix is not 3 x 3 or is null.
   */
  public NullTransformation() throws IllegalArgumentException {
    super(null);
  }
}
