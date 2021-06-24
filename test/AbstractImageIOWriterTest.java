import cs3500.imageprocessor.controller.filewriting.IImageFileWriter;
import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractImageIOWriterTest {

  protected IImageFileWriter writer;
  protected ImageInterface testCheckerboard;


  /**
   * Factory method for writer.
   *
   * @return The associated writer.
   */
  public abstract IImageFileWriter makeWriter();


  @Before
  public void setUp() throws Exception {
    writer = this.makeWriter();
    testCheckerboard = new CheckerboardGenerator(2, 2, new ArrayList<>(Arrays.asList(
        new ColorImpl(255, 0, 0), new ColorImpl(0, 255, 0)))).generateImage();
  }

  // testing a null file name
  @Test(expected = IllegalArgumentException.class)
  public void testNullFilename() throws IOException {
    writer.writeFile(null, testCheckerboard);
  }

  // testing a null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() throws IOException {
    writer.writeFile("hello", null);
  }
}