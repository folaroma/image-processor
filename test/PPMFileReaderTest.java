import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.controller.filereading.IFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.Position2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for PPMFileReader.
 */
public class PPMFileReaderTest {
  private IFileReader reader;

  @Before
  public void setUp() throws Exception {
    reader = new PPMFileReader();
  }

  //tests for exception when the given filename is null.
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileName() {
    reader.readImageFromFile(null);
  }

  // tests for exception when the given filename cannot be found
  @Test(expected = IllegalArgumentException.class)
  public void testCannotFindFileName() {
    reader.readImageFromFile("hello?.ppm");
  }

  //test for exception when the given file is not a valid PPM ASCII file
  @Test(expected = IllegalArgumentException.class)
  public void testBadPPMFile() {
    reader.readImageFromFile("test\\testreaderfiles\\badfile.txt");
  }

  // tests that reading in a proper ppm file will create an image interface with the right data
  @Test
  public void testReadingGoodPPMFile() {
    ImageInterface testImage = reader.readImageFromFile("test\\testreaderfiles\\goodfile.txt");
    assertEquals(new ColorImpl(255,123,123), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new ColorImpl(255,123,123), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new ColorImpl(255,123,123), testImage.getPixels().get(1).get(0).getColor());
    assertEquals(new ColorImpl(255,123,123), testImage.getPixels().get(1).get(1).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Position2D(j, i), testImage.getPixels().get(i).get(j).getPosition());
      }
    }
  }
}