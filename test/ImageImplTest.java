import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Testing class for ImageImpl.
 */
public class ImageImplTest {

  private final ImageInterface checkerboard = new CheckerboardGenerator(10, 10,
      new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0),
          new ColorImpl(0, 255, 0)))).generateImage();

  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */


  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    new ImageImpl(null);
  }

  // GENERAL TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------
       | getPixels Tests |
        -----------------
  */


  // getting pixels from checkerboard image rows
  @Test
  public void getPixelsCheckerboardRows() {
    assertEquals(this.checkerboard.getPixels().size(), 10);
  }

  // getting pixels from checkerboard image columns
  @Test
  public void getPixelsCheckerboardColumns() {
    assertEquals(this.checkerboard.getPixels().get(0).size(), 10);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | equals Tests |
        --------------
  */


  // testing equals with a checkerboard and a newly generated checkerboard
  @Test
  public void testEqualsCheckerboard() {
    assertTrue(this.checkerboard.equals(new CheckerboardGenerator(10, 10,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0),
            new ColorImpl(0, 255, 0)))).generateImage()));
  }

  // testing two images not equal
  @Test
  public void testNotEqualsCheckerboard() {
    assertFalse(this.checkerboard.equals(new CheckerboardGenerator(5, 5,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0),
            new ColorImpl(0, 255, 0)))).generateImage()));
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ----------------
       | hashCode Tests |
        ----------------
  */


  // testing the hashCode of checkerboard
  @Test
  public void testHashCodeCheckerboard() {
    assertEquals(this.checkerboard.hashCode(), 823490140);
  }


}