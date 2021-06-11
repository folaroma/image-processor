import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.fileReading.IFileReader;
import cs3500.imageprocessor.model.fileReading.PPMFileReader;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class ImageProcessorModelImplTest {

  private ImageProcessorModel testModel;

  @Before
  public void setUp() throws Exception {
    testModel = new ImageProcessorModelImpl("checkerboard", new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0)))));
  }

  // tests that an exception is thrown when the file name in a constructor is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullFilenameConstructor() {
    new ImageProcessorModelImpl(null, new PPMFileReader());
  }

  // tests that an exception is thrown when the file reader in a constructor is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileReaderConstructor() {
    new ImageProcessorModelImpl("res\\Koala.ppm", (IFileReader) null);
  }

  // tests that an exception is thrown when the file cannot be found
  @Test(expected = IllegalArgumentException.class)
  public void testFileCannotBeFoundConstructor() {
    new ImageProcessorModelImpl("hello?.ppm", new PPMFileReader());
  }

  // tests that an exception is thrown when the file is not a valid type of its file
  @Test(expected = IllegalArgumentException.class)
  public void testBadFileConstructor() {
    new ImageProcessorModelImpl("test\\testreaderfiles\\badfile.txt", new PPMFileReader());
  }

  // tests that exception is thrown when the name for a generated image in the constructor is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullIdGenerateConstructor() {
    new ImageProcessorModelImpl(null, new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0)))));
  }

  // tests that exception is thrown when the image generator is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullGeneratorGenerateConstructor() {
    new ImageProcessorModelImpl("test", (IFileReader) null);
  }

  // tests that exception is thrown when the list of colors in the generator is not 2.
  @Test(expected = IllegalArgumentException.class)
  public void testBadColorsGenerateConstructor() {
    new ImageProcessorModelImpl("test", new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))));
  }

  //tests that exception is thrown when the list of colors in the generator is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullColorsGenerateConstructor() {
    new ImageProcessorModelImpl("test", new CheckerboardGenerator(2, 2,
        null));
  }

  // test exception in replace image when the id is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullIdReplace() {
    testModel.replaceImage(null, new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))))
        .generateImage());
  }

  // test exception in replace image when the image is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullImageReplace() {
    testModel.replaceImage("test", null);
  }

  //tests exception if there is no image with the id contained in the map
  @Test(expected = IllegalArgumentException.class)
  public void testBadIdReplace() {
    testModel.replaceImage("test", new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))))
        .generateImage());
  }

  // tests replacing an image in the map
  @Test
  public void testReplaceImage() {
    testModel.replaceImage("checkerboard", new CheckerboardGenerator(4, 4,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))))
        .generateImage());
    assertEquals(4, testModel.getImage("checkerboard").getPixels().size());
    assertEquals(4, testModel.getImage("checkerboard").getPixels().get(0).size());
  }

  // tests exception if the id is null in getimage
  @Test(expected = IllegalArgumentException.class)
  public void testNullIdGetImage() {
    testModel.getImage(null);
  }

  // tests exception if the id is not contained
  @Test(expected = IllegalArgumentException.class)
  public void testBadIdGetImage() {
    testModel.getImage("hello?");
  }

  //tests getting the image using its id
  @Test
  public void testGetImage() {
    assertEquals(testModel.getImage("checkerboard"), new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0)))).generateImage());
  }
}