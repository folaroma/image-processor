import static org.junit.Assert.*;

import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class ImageImplTest {

  private ImageInterface checkerboard = new CheckerboardGenerator(10, 10,
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


  // getting pixels from checkerboard image
  @Test
  public void getPixelsCheckerboard() {
    assertEquals(this.checkerboard.getPixels().size(), 10);
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