import static org.junit.Assert.*;

import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.controller.filewriting.IImageFileWriter;
import cs3500.imageprocessor.controller.filewriting.JPEGImageIOWriter;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.Position2D;
import java.io.IOException;
import org.junit.Test;

public class JPEGImageIOWriterTest extends AbstractImageIOWriterTest{

  @Override
  public IImageFileWriter makeWriter() {
    return new JPEGImageIOWriter();
  }

  // tests writing to a jpeg file and reading it back in
  @Test
  public void testWritingToJPEG() throws IOException {
    writer.writeFile("test\\testreaderfiles\\writtenJPEG.jpeg", testCheckerboard);
    ImageInterface testImage = new ImageIOFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenJPEG.jpeg");
    assertEquals(new ColorImpl(98, 99, 0), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new ColorImpl(148, 149, 22), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new ColorImpl(148, 149, 22), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new ColorImpl(98, 99, 0), testImage.getPixels().get(0).get(0).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Position2D(j, i), testImage.getPixels().get(i).get(j).getPosition());
      }
    }
  }
}