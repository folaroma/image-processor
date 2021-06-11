import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for ImageProcessorModel.
 */
public class ImageProcessorModelImplTest {

  private ImageProcessorModel testModel;

  @Before
  public void setUp() throws Exception {
    testModel = new ImageProcessorModelImpl();
    testModel.addImage("checkerboard", testModel.generateCheckerboard(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0)))));
    ImageInterface blackRedCheckerBoard = new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))))
        .generateImage();
    ImageInterface greenRedCheckerBoard = new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 255, 0))))
        .generateImage();
    testModel.addImage("blackRedCheckerBoard", blackRedCheckerBoard);
    testModel.addImage("greenRedCheckerBoard", greenRedCheckerBoard);
  }

  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */

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

  // tests for exception with null id grayscale
  @Test(expected = IllegalArgumentException.class)
  public void testNullIdGrayscale() {
    testModel.grayscale(null);
  }

  // tests for exception with bad id grayscale
  @Test(expected = IllegalArgumentException.class)
  public void testBadIDGrayscale() {
    testModel.grayscale("a");
  }

  // tests for exception with null id sepia
  @Test(expected = IllegalArgumentException.class)
  public void testNullIdSepia() {
    testModel.sepia(null);
  }

  // tests for exception with bad id grayscale
  @Test(expected = IllegalArgumentException.class)
  public void testBadIDSepia() {
    testModel.sepia("a");
  }

  // tests exception in add image when the id is null
  @Test(expected = IllegalArgumentException.class)
  public void nullIdAddImage() {
    testModel.addImage(null, testModel.getImage("checkerboard"));
  }

  // tests exception in add image when the image is null
  @Test(expected = IllegalArgumentException.class)
  public void nullImageAddImage() {
    testModel.addImage("checkerboard", null);
  }

  // tests exception in add image when the id is already contained
  @Test(expected = IllegalArgumentException.class)
  public void badIdAddImage() {
    testModel.addImage("checkerboard", this.testModel.getImage("checkerboard"));
  }

  //tests generating a checkerboard with null colors
  @Test(expected = IllegalArgumentException.class)
  public void testNullColorsCheckerboard() {
    testModel.generateCheckerboard(4, 4, null);
  }

  //tests generating a checkerboard with more than 2 colors
  @Test(expected = IllegalArgumentException.class)
  public void testMoreThan2ColorsCheckerboard() {
    testModel.generateCheckerboard(4, 4,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0),
            new ColorImpl(0, 255, 255))));
  }

  //tests generating a checkerboard with more than less than 2 rows
  @Test(expected = IllegalArgumentException.class)
  public void testLessThan2RowsCheckerboard() {
    testModel.generateCheckerboard(1, 4,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))));
  }

  //tests generating a checkerboard with more than less than 2 columns
  @Test(expected = IllegalArgumentException.class)
  public void testLessThan2ColumnsCheckerboard() {
    testModel.generateCheckerboard(2, 1,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))));
  }

  // GENERAL TESTS

  // -----------------------------------------------------------------------------------------------


  //tests getting the image using its id
  @Test
  public void testGetImage() {
    assertEquals(testModel.getImage("checkerboard"), new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))))
        .generateImage());
  }

  // tests adding an image to the map
  @Test
  public void testAddImage() {
    ImageInterface otherCheckerboard = testModel.generateCheckerboard(4, 4,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0), new ColorImpl(0, 0, 0))));
    testModel.addImage("checkerboard2", otherCheckerboard);
    assertEquals(testModel.getImage("checkerboard2"), otherCheckerboard);
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

    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getRed(),
        255);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(1).getColor().getRed(),
        126);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(0).getColor().getRed(),
        126);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(1).getColor().getRed(),
        255);

    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getGreen(),
        0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(1).getColor().getGreen(),
        0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(0).getColor().getGreen(),
        0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(1).getColor().getGreen(),
        0);

    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(0).getColor().getBlue(),
        0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(0).get(1).getColor().getBlue(),
        0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(0).getColor().getBlue(),
        0);
    assertEquals(testModel.sharpen("checkerboard").getPixels().get(1).get(1).getColor().getBlue(),
        0);

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

  // tests running a grayscale transformation on a 4x4 black red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testGrayscaleBlackRedCheckerboard() {
    ImageInterface grayCheckerboard = testModel.grayscale("blackRedCheckerBoard");
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getBlue());

  }

  // tests running a grayscale transformation on a 4x4 green red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testGrayscaleGreenRedCheckerboard() {
    ImageInterface grayCheckerboard = testModel.grayscale("greenRedCheckerBoard");
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(182, grayCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(182, grayCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(182, grayCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(182, grayCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(182, grayCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(182, grayCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getBlue());

  }

  //tests that stacking grayscale on top of each other and that it stays the same.
  @Test
  public void testGrayscaleStacking() {
    ImageInterface grayCheckerboard = testModel.grayscale("blackRedCheckerBoard");
    testModel.addImage("gray", grayCheckerboard);
    ImageInterface grayCheckerboard2 = testModel.grayscale("gray");
    assertEquals(54, grayCheckerboard2.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard2.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard2.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, grayCheckerboard2.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, grayCheckerboard2.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, grayCheckerboard2.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, grayCheckerboard2.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, grayCheckerboard2.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, grayCheckerboard2.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(54, grayCheckerboard2.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard2.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard2.getPixels().get(0).get(0).getColor().getBlue());


  }


  // tests running a sepia transformation on a 4x4 black red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testSepiaBlackRedCheckerboard() {
    ImageInterface sepiaCheckerboard = testModel.sepia("blackRedCheckerBoard");
    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, sepiaCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());


  }

  // tests running a grayscale transformation on a 4x4 green red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testSepiaGreenRedCheckerboard() {
    ImageInterface sepiaCheckerboard = testModel.sepia("greenRedCheckerBoard");

    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(196, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(174, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(136, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(196, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(174, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(136, sepiaCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(100, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(88, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(69, sepiaCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

  }

  //tests that stacking sepia on top of each other.
  @Test
  public void testSepiaStacking() {
    ImageInterface sepiaCheckerboard = testModel.sepia("blackRedCheckerBoard");
    testModel.addImage("sepia", sepiaCheckerboard);
    ImageInterface sepia2Checkerboard = testModel.sepia("sepia");
    assertEquals(120, sepia2Checkerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(106, sepia2Checkerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(83, sepia2Checkerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, sepia2Checkerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, sepia2Checkerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, sepia2Checkerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, sepia2Checkerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, sepia2Checkerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, sepia2Checkerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(120, sepia2Checkerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(106, sepia2Checkerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(83, sepia2Checkerboard.getPixels().get(0).get(0).getColor().getBlue());
  }

  // test to make sure generateImage creates the correct amount of rows for checkerboard
  @Test
  public void fiveRowsCheckerboard() {
    ImageInterface checkerboard = testModel.generateCheckerboard(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255))));

    assertEquals(checkerboard.getPixels().size(), 5);

  }

  // test to make sure generateImage creates the correct amount of columns for checkerboard
  @Test
  public void fiveColumnsCheckerboard() {
    ImageInterface checkerboard = testModel.generateCheckerboard(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255))));

    assertEquals(checkerboard.getPixels().get(0).size(), 5);

  }

  // test to make sure generateImage creates a checkerboard with alternating colors
  @Test
  public void firstColorCheckerBoardWhite() {
    ImageInterface checkerboard = testModel.generateCheckerboard(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255))));

    assertEquals(checkerboard.getPixels().get(0).get(0).getColor(), new ColorImpl(0, 0, 0));

  }

  // test to make sure generateImage creates a checkerboard with alternating colors
  @Test
  public void secondColorCheckerBoardBlack() {
    ImageInterface checkerboard = testModel.generateCheckerboard(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255))));

    assertEquals(checkerboard.getPixels().get(0).get(1).getColor(), new ColorImpl(255, 255, 255));

  }


}