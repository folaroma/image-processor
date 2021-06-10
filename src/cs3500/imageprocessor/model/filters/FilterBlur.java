package cs3500.imageprocessor.model.filters;


/**
 * Class representing a blur filter than can be applied to an image.
 */
public class FilterBlur extends AbstractFilter {

  /**
   * Creates an instance of the blur filter using the associated matrix for the blur.
   */
  public FilterBlur() {
    super(new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}});
  }



}
