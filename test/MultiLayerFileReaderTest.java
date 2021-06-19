import static org.junit.Assert.*;

import cs3500.imageprocessor.controller.filereading.IMultiLayerReader;
import cs3500.imageprocessor.controller.filereading.MultiLayerFileReader;
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
}