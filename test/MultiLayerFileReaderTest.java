import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.controller.filereading.IMultiLayerReader;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.MultiLayerFileReader;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for MultiLayerFileReader.
 */
public class MultiLayerFileReaderTest {

  private IMultiLayerReader reader;

  @Before
  public void setUp() throws Exception {
    reader = new MultiLayerFileReader();
  }

  // tests exception will null filename
  @Test(expected = IllegalArgumentException.class)
  public void nullFilename() {
    reader.readImages(null);
  }

  // tests exception will null filename
  @Test(expected = IllegalArgumentException.class)
  public void badFilename() {
    reader.readImages("hello.txt");
  }

  // tests exception with malformed txt file
  @Test(expected = IllegalArgumentException.class)
  public void badFormedFile() {
    reader.readImages("test\\testreaderfiles\\badmultifile.txt");
  }

  // tests importing a valid multi layer image
  @Test
  public void goodMultiImageFile() {
    Map<String, ImageInterface> layers = reader.readImages("test\\testreaderfiles\\desert3\\desert3.txt");
    assertEquals(layers.get("first"),
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\desert3\\first.jpeg"));
    assertEquals(layers.get("second"),
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\desert3\\second.jpeg"));

    List<String> visibility = reader.readVisibility();
    assertEquals(visibility.size(), 0);
  }
}