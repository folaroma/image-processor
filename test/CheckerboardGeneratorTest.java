import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Testing class for CheckerboardGenerator.
 */
public class CheckerboardGeneratorTest {

  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */


  // testing null color exception
  @Test(expected = IllegalArgumentException.class)
  public void nullColorEx() {
    new CheckerboardGenerator(0, 0, null);
  }

  // testing less than 2 colors for checkerboard
  @Test(expected = IllegalArgumentException.class)
  public void lessThanTwoColors() {
    new CheckerboardGenerator(0, 0, new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0))));
  }

  // testing more than 2 colors for checkerboard
  @Test(expected = IllegalArgumentException.class)
  public void moreThanTwoColors() {
    new CheckerboardGenerator(0, 0, new ArrayList<>(
        Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(1, 0, 0),
            new ColorImpl(0, 1, 0))));
  }

  // GENERAL TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        ---------------------
       | generateImage Tests |
        ---------------------
  */


  // test to make sure generateImage creates the correct amount of rows for checkerboard
  @Test
  public void fiveRowsCheckerboard() {
    ImageInterface checkerboard = new CheckerboardGenerator(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))).generateImage();

    assertEquals(checkerboard.getPixels().size(), 5);

  }

  // test to make sure generateImage creates the correct amount of columns for checkerboard
  @Test
  public void fiveColumnsCheckerboard() {
    ImageInterface checkerboard = new CheckerboardGenerator(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))).generateImage();

    assertEquals(checkerboard.getPixels().get(0).size(), 5);

  }

  // test to make sure generateImage creates a checkerboard with alternating colors
  @Test
  public void firstColorCheckerBoardWhite() {
    ImageInterface checkerboard = new CheckerboardGenerator(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))).generateImage();

    assertEquals(checkerboard.getPixels().get(0).get(0).getColor(), new ColorImpl(0, 0, 0));

  }

  // test to make sure generateImage creates a checkerboard with alternating colors
  @Test
  public void secondColorCheckerBoardBlack() {
    ImageInterface checkerboard = new CheckerboardGenerator(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))).generateImage();

    assertEquals(checkerboard.getPixels().get(0).get(1).getColor(), new ColorImpl(255, 255, 255));

  }


}