package cs3500.imageprocessor.model.colortransformations;

/**
 * Class to represent a sepia color transformation on an image.
 */
public class SepiaTransformation extends AbstractColorTransformation {

  /**
   * Creates an instance of the sepia transformation, with the matrix that produces this effect.
   */
  public SepiaTransformation() {
    super(new double[][]{{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}});
  }

}
