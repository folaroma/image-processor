import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.colorTransformations.GrayscaleTransformation;
import cs3500.imageprocessor.model.fileReading.PPMFileReader;
import cs3500.imageprocessor.model.fileWriting.PPMFileWriter;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for GrayScaleTransformation function objects.
 */
public class GrayscaleTransformationTest {
 private ImageInterface simpleCheckerboard;
 private GrayscaleTransformation testGrayscale;

  @Before
  public void setUp() throws Exception {
     simpleCheckerboard = new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))))
        .generateImage();
     testGrayscale = new GrayscaleTransformation();
  }

  // tests running a grayscale transformation on a 4x4 checkerboard.
  @Test
  public void testGrayscaleSimpleCheckerboard() throws IOException {
   ImageInterface grayCheckerboard = testGrayscale.applyTransformation(simpleCheckerboard);
     assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getRed());
     assertEquals(54,  grayCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
     assertEquals(54,  grayCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

     assertEquals(0,  grayCheckerboard.getPixels().get(0).get(1).getColor().getRed());
     assertEquals(0,  grayCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
     assertEquals(0,  grayCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0,  grayCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0,  grayCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0,  grayCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(54,  grayCheckerboard.getPixels().get(1).get(1).getColor().getRed());
    assertEquals(54,  grayCheckerboard.getPixels().get(1).get(1).getColor().getGreen());
    assertEquals(54,  grayCheckerboard.getPixels().get(1).get(1).getColor().getBlue());

  }
}