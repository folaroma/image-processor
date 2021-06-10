package cs3500.imageprocessor.model.filters;

/**
 * Class representing a sharpen filter than can be applied to images.
 */
public class FilterSharpen extends AbstractFilter {

  /**
   * Creates an instance of a sharpen filter using the associated matrix for sharpening.
   */
  public FilterSharpen() {
    super(new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.12},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}});
  }
}
