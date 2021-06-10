import cs3500.imageprocessor.model.filters.AbstractFilter;

public class BadFilter extends AbstractFilter {

  /**
   * Creates and instance of the abstract filter with the given matrix.
   *
   * @throws IllegalArgumentException If th matrix is null, it does not have odd dimensions, or it is
   *                                  not square.
   */
  public BadFilter(double[][] matrix) throws IllegalArgumentException {
    super(matrix);
  }

}
