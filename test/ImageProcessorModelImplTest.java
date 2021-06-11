import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.imageGenerating.IImageGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
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


  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */


  // tests that exception is thrown when the name for a generated image in the constructor is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullIdGenerateConstructor() {
    new ImageProcessorModelImpl(null, new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0)))));
  }

  // tests that exception is thrown when the image generator is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullGeneratorGenerateConstructor() {
    new ImageProcessorModelImpl("test", (IImageGenerator) null);
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

  // testing blur id exception
  @Test(expected = IllegalArgumentException.class)
  public void badIdBlur() {
    testModel.blur("a");
  }

  // testing null id blur exception
  @Test(expected = IllegalArgumentException.class)
  public void nullIdBlur() {
    testModel.blur(null);
  }

  // testing sharpen id exception
  @Test(expected = IllegalArgumentException.class)
  public void badIdSharpen() {
    testModel.sharpen("a");
  }

  // testing null id sharpen exception
  @Test(expected = IllegalArgumentException.class)
  public void nullIdSharpen() {
    testModel.sharpen(null);
  }



  // GENERAL TESTS

  // -----------------------------------------------------------------------------------------------


  //tests getting the image using its id
  @Test
  public void testGetImage() {
    assertEquals(testModel.getImage("checkerboard"), new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0)))).generateImage());
  }

  // testing general blurring, entire board
  @Test
  public void applyBlurAll() {

    assertEquals(testModel.blur("checkerboard").getPixels().get(0).get(0).getColor().getRed(), 78);
    assertEquals(testModel.blur("checkerboard").getPixels().get(0).get(1).getColor().getRed(), 62);
    assertEquals(testModel.blur("checkerboard").getPixels().get(1).get(0).getColor().getRed(), 62);
    assertEquals(testModel.blur("checkerboard").getPixels().get(1).get(1).getColor().getRed(), 78);

    assertEquals(testModel.blur("checkerboard").getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(testModel.blur("checkerboard").getPixels().get(0).get(1).getColor().getGreen(), 0);
    assertEquals(testModel.blur("checkerboard").getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(testModel.blur("checkerboard").getPixels().get(1).get(1).getColor().getGreen(), 0);

    assertEquals(testModel.blur("checkerboard").getPixels().get(0).get(0).getColor().getBlue(), 0);
    assertEquals(testModel.blur("checkerboard").getPixels().get(0).get(1).getColor().getBlue(), 0);
    assertEquals(testModel.blur("checkerboard").getPixels().get(1).get(0).getColor().getBlue(), 0);
    assertEquals(testModel.blur("checkerboard").getPixels().get(1).get(1).getColor().getBlue(), 0);

  }

  // testing general blurring red
  @Test
  public void applyBlurRed() {
    assertEquals(
        testModel.blur("checkerboard").getPixels().get(0).get(1).getColor().getRed(), 62);
  }

  // testing general blurring green
  @Test
  public void applyBlurGreen() {
    assertEquals(
        testModel.blur("checkerboard").getPixels().get(0).get(1).getColor().getGreen(), 0);
  }

  // testing general blurring blue
  @Test
  public void applyBlurBlue() {
    assertEquals(
        testModel.blur("checkerboard").getPixels().get(0).get(1).getColor().getBlue(), 0);
  }

  // testing general blurring, twice red
  @Test
  public void applyBlurTwiceRed() {

    ImageInterface checkerboardBlur = testModel.blur("checkerboard");
    testModel.addImage("checkerboardBlur", checkerboardBlur);

    assertEquals(testModel.blur("checkerboardBlur")
        .getPixels().get(0).get(1).getColor().getRed(), 36);

  }

  // testing general blurring, twice green
  @Test
  public void applyBlurTwiceGreen() {

    ImageInterface checkerboardBlur = testModel.blur("checkerboard");
    testModel.addImage("checkerboardBlur", checkerboardBlur);

    assertEquals(testModel.blur("checkerboardBlur")
        .getPixels().get(0).get(1).getColor().getGreen(), 0);

  }

  // testing general blurring, twice blue
  @Test
  public void applyBlurTwiceBlue() {

    ImageInterface checkerboardBlur = testModel.blur("checkerboard");
    testModel.addImage("checkerboardBlur", checkerboardBlur);

    assertEquals(testModel.blur("checkerboard")
        .getPixels().get(0).get(1).getColor().getBlue(), 0);

  }


  // testing general sharpening, entire board
  @Test
  public void applySharpenAll() {

    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(1).getColor().getRed(), 126);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(0).getColor().getRed(), 126);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(1).getColor().getRed(), 255);

    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(1).getColor().getGreen(), 0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(1).getColor().getGreen(), 0);

    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getBlue(), 0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(1).getColor().getBlue(), 0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(0).getColor().getBlue(), 0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(1).getColor().getBlue(), 0);

  }

  // testing general sharpening red
  @Test
  public void applySharpenRed() {
    assertEquals(
        testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getRed(), 255);
  }

  // testing general sharpening green
  @Test
  public void applySharpenGreen() {
    assertEquals(
        testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getGreen(), 0);
  }

  // testing general sharpening blue
  @Test
  public void applySharpenBlue() {
    assertEquals(
        testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getBlue(), 0);
  }

  // testing general sharpening, twice red
  @Test
  public void applySharpenTwiceRed() {
    ImageInterface checkerboardBlur = testModel.blur("checkerboard");
    testModel.addImage("checkerboardBlur", checkerboardBlur);

    assertEquals(testModel.sharpen("checkerboard")
        .getPixels().get(0).get(0).getColor().getRed(), 255);

  }

  // testing general sharpening, twice green
  @Test
  public void applySharpenTwiceGreen() {
    ImageInterface checkerboardBlur = testModel.blur("checkerboard");
    testModel.addImage("checkerboardBlur", checkerboardBlur);

    assertEquals(testModel.sharpen("checkerboard")
        .getPixels().get(0).get(0).getColor().getGreen(), 0);

  }

  // testing general sharpening, twice blue
  @Test
  public void applySharpenTwiceBlue() {
    ImageInterface checkerboardBlur = testModel.blur("checkerboard");
    testModel.addImage("checkerboardBlur", checkerboardBlur);

    assertEquals(testModel.sharpen("checkerboard")
        .getPixels().get(0).get(0).getColor().getBlue(), 0);

  }


}