import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing class for an AbstractColorTransformation.
 */
public class AbstractColorTransformationTest {

  // tests that creating a color transformation with a nonsquare matrix results in an exception.
  @Test(expected = IllegalArgumentException.class)
  public void testBadColorTransformationNonSquare() {
    new NonSquareColorTransformation();
  }

  // tests that creating a color transformation with a square but not 3 x 3 matrix matrix results in an exception.
  @Test(expected = IllegalArgumentException.class)
  public void testBadColorTransformationNonThreeByThree() {
    new NotThreeByThreeColorTransformation();
  }


}