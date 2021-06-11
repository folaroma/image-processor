import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.colortransformations.SepiaTransformation;
import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for SepiaTransformation.
 */
public class SepiaTransformationTest {

  private ImageInterface blackRedCheckerBoard;
  private ImageInterface greenRedCheckerBoard;
  private SepiaTransformation testSepia;

  @Before
  public void setUp() throws Exception {
    blackRedCheckerBoard = new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))))
        .generateImage();
    greenRedCheckerBoard = new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 255, 0))))
        .generateImage();
    testSepia = new SepiaTransformation();
  }

  // tests for exception with null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    testSepia.applyTransformation(null);
  }

  // tests running a sepia transformation on a 4x4 black red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testSepiaBlackRedCheckerboard() {
    ImageInterface sepiaCheckerboard = testSepia.applyTransformation(blackRedCheckerBoard);
    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());


  }

  // tests running a grayscale transformation on a 4x4 green red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testSepiaGreenRedCheckerboard() {
    ImageInterface sepiaCheckerboard = testSepia.applyTransformation(greenRedCheckerBoard);

    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(196, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(174, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(136, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(196, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(174, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(136, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

  }

  //tests that stacking sepia on top of each other.
  @Test
  public void testSepiaStacking() {
    ImageInterface sepiaCheckerboard = testSepia
        .applyTransformation(testSepia.applyTransformation(blackRedCheckerBoard));
    assertEquals(120, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(106, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(83, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(120, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(106, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(83, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());
  }
}