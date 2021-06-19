import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.controller.filereading.IFileReader;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filewriting.JPEGImageIOWriter;
import cs3500.imageprocessor.controller.filewriting.PNGImageIOWriter;
import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for ImageIOFileReader.
 */
public class ImageIOFileReaderTest {
  private IFileReader reader;

  @Before
  public void setUp() throws Exception {
    reader = new ImageIOFileReader();
  }

  // tests exception for null filename
  @Test(expected = IllegalArgumentException.class)
  public void nullFileName() throws IOException {
    reader.readImageFromFile(null);
  }

  // tests exception for  filename that does not exist
  @Test(expected = IllegalArgumentException.class)
  public void badFilename() throws IOException {
    reader.readImageFromFile("hello?.png");
  }

  // tests for an image file that is not supported by ImageIO
  @Test(expected = IllegalArgumentException.class)
  public void badFileType() throws IOException {
    reader.readImageFromFile("res\\bug.ppm");
  }

  //test reading in a png file
  @Test
  public void goodPNGFileTest() throws IOException {
    new PNGImageIOWriter().writeFile("res\\checkerboard.png", new CheckerboardGenerator(2, 2, new ArrayList<>(
        Arrays.asList(new ColorImpl(255 ,0, 0), new ColorImpl(255, 255, 255)))).generateImage());
   ImageInterface board = reader.readImageFromFile("res\\checkerboard.png");
   assertEquals(2, board.getPixels().size());
    assertEquals(2, board.getPixels().get(0).size());
    assertEquals(new ColorImpl(255, 0,0), board.getPixels().get(0).get(0).getColor());
  }

  //test reading in a jpeg file, Colors change due tp jpeg compression.
  @Test
  public void goodJPEGFileTest() throws IOException {
    new JPEGImageIOWriter().writeFile("res\\checkerboard.jpeg", new CheckerboardGenerator(2, 2, new ArrayList<>(
        Arrays.asList(new ColorImpl(255 ,0, 0), new ColorImpl(255, 255, 255)))).generateImage());
    ImageInterface board = reader.readImageFromFile("res\\checkerboard.jpeg");
    assertEquals(2, board.getPixels().size());
    assertEquals(2, board.getPixels().get(0).size());
    assertEquals(new ColorImpl(161, 35,36), board.getPixels().get(0).get(0).getColor());
  }
}