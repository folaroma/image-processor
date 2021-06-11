import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.colorTransformations.GrayscaleTransformation;
import cs3500.imageprocessor.model.fileReading.PPMFileReader;
import cs3500.imageprocessor.model.fileWriting.IImageFileWriter;
import cs3500.imageprocessor.model.fileWriting.PPMFileWriter;
import cs3500.imageprocessor.model.filters.FilterBlur;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.Position2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for PPMFileWriter.
 */
public class PPMFileWriterTest {

  private IImageFileWriter writer;
  private ImageInterface testCheckerboard;

  @Before
  public void setUp() throws Exception {
    writer = new PPMFileWriter();
    testCheckerboard = new CheckerboardGenerator(2, 2, new ArrayList<>(Arrays.asList(
        new ColorImpl(255, 0, 0), new ColorImpl(0, 255, 0)))).generateImage();
  }

  // test that exception is thrown when the filename is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileName() throws IOException {
    writer.writeFile(null, testCheckerboard);
  }

  // tests that exception is thrown when the image is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() throws IOException {
    writer.writeFile("res\\test.ppm", null);
  }

  // tests that file written from the image will return the same information after being read in again as a PPM file.
  @Test
  public void testWritingToFileAsPPM() throws IOException {
    writer.writeFile("test\\testreaderfiles\\writtenfile.txt", testCheckerboard);
    ImageInterface testImage = new PPMFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenfile.txt");
    assertEquals(new ColorImpl(255, 0, 0), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new ColorImpl(0, 255, 0), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new ColorImpl(0, 255, 0), testImage.getPixels().get(1).get(0).getColor());
    assertEquals(new ColorImpl(255, 0, 0), testImage.getPixels().get(1).get(1).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Position2D(j, i), testImage.getPixels().get(i).get(j).getPosition());
      }
    }
  }

  // tests writing a filtered image
  @Test
  public void testWritingToFileAsPPMFiltered() throws IOException {
    ImageInterface blurredCheckerboard = new FilterBlur().applyFilter(testCheckerboard);
    writer.writeFile("test\\testreaderfiles\\writtenfile1.txt", blurredCheckerboard);
    ImageInterface testImage = new PPMFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenfile1.txt");
    assertEquals(new ColorImpl(78 ,62, 0), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new ColorImpl(62, 78, 0), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new ColorImpl(62, 78, 0), testImage.getPixels().get(1).get(0).getColor());
    assertEquals(new ColorImpl(78, 62, 0), testImage.getPixels().get(1).get(1).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Position2D(j, i), testImage.getPixels().get(i).get(j).getPosition());
      }
    }
  }

  // tests writing a transformed image
  @Test
  public void testWritingToFileAsPPMBlurred() throws IOException {
    ImageInterface grayCheckerboard = new GrayscaleTransformation().applyTransformation(testCheckerboard);
    writer.writeFile("test\\testreaderfiles\\writtenfile2.txt", grayCheckerboard);
    ImageInterface testImage = new PPMFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenfile2.txt");
    assertEquals(new ColorImpl(54 ,54, 54), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new ColorImpl(182, 182, 182), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new ColorImpl(182, 182, 182), testImage.getPixels().get(1).get(0).getColor());
    assertEquals(new ColorImpl(54, 54, 54), testImage.getPixels().get(1).get(1).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Position2D(j, i), testImage.getPixels().get(i).get(j).getPosition());
      }
    }
  }
}