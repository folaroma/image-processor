import static org.junit.Assert.*;

import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.awt.Color;
import org.junit.Test;

public class PixelImplTest {

  private final IPixel pixelWhite = new PixelImpl(new Position2D(0, 0), new ColorImpl(0, 0, 0));

  private final IPixel pixelBlack = new PixelImpl(new Position2D(1, 0), new ColorImpl(255, 255, 255));

  private final IPixel pixelRed = new PixelImpl(new Position2D(0, 1), new ColorImpl(255, 0, 0));


  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */


  // testing null position constructor
  @Test(expected = IllegalArgumentException.class)
  public void nullPositionEx() {
    new PixelImpl(null, new ColorImpl(0, 0, 0));
  }

  // testing null color constructor
  @Test(expected = IllegalArgumentException.class)
  public void nullColorEx() {
    new PixelImpl(new Position2D(0, 0), null);
  }

  // testing null position and color constructor
  @Test(expected = IllegalArgumentException.class)
  public void nullPositionAndColorEx() {
    new PixelImpl(null, null);
  }


  // GENERAL TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -------------------
       | getPosition Tests |
        -------------------
  */


  // getting position of white pixel
  @Test
  public void getPositionWhite() {
    assertEquals(this.pixelWhite.getPosition(), new Position2D(0, 0));
  }

  // getting position of black pixel
  @Test
  public void getPositionBlack() {
    assertEquals(this.pixelBlack.getPosition(), new Position2D(1, 0));
  }

  // getting position of red pixel
  @Test
  public void getPositionRed() {
    assertEquals(this.pixelRed.getPosition(), new Position2D(0, 1));
  }


  // -----------------------------------------------------------------------------------------------


  /*
        ----------------
       | getColor Tests |
        ----------------
  */


  // getting the color of a white pixel
  @Test
  public void getColorWhite() {
    assertEquals(this.pixelWhite.getColor(), new ColorImpl(0, 0, 0));
  }

  // getting the color of a black pixel
  @Test
  public void getColorBlack() {
    assertEquals(this.pixelBlack.getColor(), new ColorImpl(255, 255, 255));
  }

  // getting the color of a red pixel
  @Test
  public void getColorRed() {
    assertEquals(this.pixelRed.getColor(), new ColorImpl(255, 0, 0));

  }


  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | equals Tests |
        --------------
  */


  // testing that a white pixel is equal to another white pixel with the same Position
  @Test
  public void testEqualsWhite() {
    assertTrue(this.pixelWhite.equals(new PixelImpl(new Position2D(0, 0), new ColorImpl(0, 0, 0))));
  }

  // testing that a white pixel is not equal to a red pixel
  @Test
  public void testNotEqualsWhiteRed() {
    assertFalse(this.pixelWhite.equals(this.pixelRed));
  }


  // -----------------------------------------------------------------------------------------------


  /*
        ----------------
       | hashCode Tests |
        ----------------
  */


  // testing hashCode of a white pixel
  @Test
  public void testWhiteHashCode() {
    assertEquals(this.pixelWhite.hashCode(), 60543);
  }

  // testing that the hashCode of a white pixel is not the same as a red pixel
  @Test
  public void testWhiteHashCodeNotRed() {
    assertNotEquals(this.pixelWhite.hashCode(), this.pixelRed.hashCode());
  }


}