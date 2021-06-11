import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import testingtransformations.NonSquareColorTransformation;
import testingtransformations.NotThreeByThreeColorTransformation;
import testingtransformations.NullTransformation;
import testingtransformations.OverLimitTransformation;
import testingtransformations.UnderLimitTransformation;

/**
 * Testing class for an AbstractColorTransformation.
 */
public class AbstractColorTransformationTest {

  private ImageInterface greenRedCheckerBoard;

  @Before
  public void setUp() throws Exception {
    greenRedCheckerBoard = new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 255, 0))))
        .generateImage();
  }

  // tests that creating a color transformation with a nonsquare matrix results in an exception.
  @Test(expected = IllegalArgumentException.class)
  public void testBadColorTransformationNonSquare() {
    new NonSquareColorTransformation();
  }

  // tests that creating a color transformation with a square but not
  // 3 x 3 matrix matrix results in an exception.
  @Test(expected = IllegalArgumentException.class)
  public void testBadColorTransformationNonThreeByThree() {
    new NotThreeByThreeColorTransformation();
  }

  //test that create a transformation with null matrix results in an exception.
  @Test(expected = IllegalArgumentException.class)
  public void nullTransformation() {
    new NullTransformation();
  }

  // tests that color values are clamped to 255 when they exceed 255.
  @Test
  public void testClampingOverLimit() {
    ImageInterface overLimitBoard = new OverLimitTransformation()
        .applyTransformation(greenRedCheckerBoard);
    assertEquals(255, overLimitBoard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(255, overLimitBoard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(255, overLimitBoard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(255, overLimitBoard.getPixels().get(1).get(1).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(1).get(1).getColor().getGreen());
    assertEquals(255, overLimitBoard.getPixels().get(1).get(1).getColor().getBlue());
  }

  // tests that color values are clamped to 0 when they are below 0.
  @Test
  public void testClampingUnderLimit() {
    ImageInterface underLimitBoard = new UnderLimitTransformation()
        .applyTransformation(greenRedCheckerBoard);
    assertEquals(0, underLimitBoard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(0, underLimitBoard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(0, underLimitBoard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, underLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, underLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, underLimitBoard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, underLimitBoard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, underLimitBoard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, underLimitBoard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(0, underLimitBoard.getPixels().get(1).get(1).getColor().getRed());
    assertEquals(0, underLimitBoard.getPixels().get(1).get(1).getColor().getGreen());
    assertEquals(0, underLimitBoard.getPixels().get(1).get(1).getColor().getBlue());
  }


}