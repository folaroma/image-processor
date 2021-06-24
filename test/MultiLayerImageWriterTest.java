import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filewriting.IMultiLayerImageWriter;
import cs3500.imageprocessor.controller.filewriting.MultiLayerImageWriter;
import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class MultiLayerImageWriterTest {

  private IMultiLayerImageWriter writer;
  private ImageInterface testCheckerboard;
  private Map<String, ImageInterface> layers;
  private ArrayList<String> visibility;

  @Before
  public void setUp() throws Exception {
    writer = new MultiLayerImageWriter();
    testCheckerboard = new CheckerboardGenerator(2, 2, new ArrayList<>(Arrays.asList(
        new ColorImpl(255, 0, 0), new ColorImpl(0, 255, 0)))).generateImage();
    this.layers = new HashMap<>();
    visibility = new ArrayList<>();
  }

  // tests for null filename
  @Test(expected = IllegalArgumentException.class)
  public void testNullFilename() throws IOException {
    writer.writeFile(null, "png", this.layers, this.visibility);
  }

  // tests for null type
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileType() throws IOException {
    writer.writeFile("name", null, this.layers, this.visibility);
  }

  // tests for null map
  @Test(expected = IllegalArgumentException.class)
  public void testNullLayers() throws IOException {
    writer.writeFile("name", "png", null, this.visibility);
  }

  // tests for null visibility list
  @Test(expected = IllegalArgumentException.class)
  public void testNullList() throws IOException {
    writer.writeFile("name", "png", this.layers, null);
  }

  // tests for exception with an invalid file type.
  @Test(expected = IllegalArgumentException.class)
  public void testBadFileType() throws IOException {
    this.layers.put("1", testCheckerboard);
    writer.writeFile("name", "dfgdfgdfgdfg", this.layers, this.visibility);
  }

  // tests that writing a multi layer image creates the txt file and the image file.
  @Test
  public void testGoodMultiLayerFile() throws IOException {
    this.layers.put("1", testCheckerboard);
    this.visibility.add("1");
    writer.writeFile("test\\testreaderfiles\\checkerboard", "png", this.layers, this.visibility);

    assertEquals(testCheckerboard,
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\checkerboard\\1.png"));
    assertEquals("png\ntest\\testreaderfiles\\checkerboard\\1.png 1 invisible\n", Files.readString(
        Path.of("test\\testreaderfiles\\checkerboard\\test\\testreaderfiles\\checkerboard.txt")));
  }
}