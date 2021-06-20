import static org.junit.Assert.*;

import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filewriting.IImageFileWriter;
import cs3500.imageprocessor.controller.filewriting.PNGImageIOWriter;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.Position2D;
import java.io.IOException;
import org.junit.Test;

public class PNGImageIOWriterTest extends AbstractImageIOWriterTest{

  @Override
  public IImageFileWriter makeWriter() {
    return new PNGImageIOWriter();
  }

  // tests writing to a png
  @Test
  public void testWritingPNG() throws IOException {
    writer.writeFile("test\\testreaderfiles\\writtenPNG.png", testCheckerboard);
    ImageInterface testImage = new ImageIOFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenPNG.png");
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
}