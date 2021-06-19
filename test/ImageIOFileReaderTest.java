import static org.junit.Assert.*;

import cs3500.imageprocessor.controller.filereading.IFileReader;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import org.junit.Before;

public class ImageIOFileReaderTest {
  private IFileReader reader;

  @Before
  public void setUp() throws Exception {
    reader = new ImageIOFileReader();
  }
}