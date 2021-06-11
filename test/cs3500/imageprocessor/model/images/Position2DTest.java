package cs3500.imageprocessor.model.images;

import static org.junit.Assert.*;

import org.junit.Test;

public class Position2DTest {

  private final Position2D zeroZero = new Position2D(0, 0);

  private final Position2D oneZero = new Position2D(1, 0);

  private final Position2D zeroOne = new Position2D(0, 1);



  // -----------------------------------------------------------------------------------------------


  /*
        -----------
       | getXTests |
        -----------
  */


  // getting the x of Position 0 0
  @Test
  public void getXZeroZero() {
    assertEquals(this.zeroZero.getX(), 0);
  }

  // getting the x of Position 1 0
  @Test
  public void getXOneZero() {
    assertEquals(this.oneZero.getX(), 1);
  }

  // getting the x of Position 0 1
  @Test
  public void getXZeroOne() {
    assertEquals(this.zeroOne.getX(), 0);
  }


  // -----------------------------------------------------------------------------------------------


  /*
        ------------
       | getY Tests |
        ------------
  */


  // getting the y of Position 0 0
  @Test
  public void getYZeroZero() {
    assertEquals(this.zeroZero.getX(), 0);
  }

  // getting the y of Position 1 0
  @Test
  public void getYOneZero() {
    assertEquals(this.oneZero.getX(), 1);
  }

  // getting the y of Position 0 1
  @Test
  public void getYZeroOne() {
    assertEquals(this.zeroOne.getY(), 1);
  }


  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | equals Tests |
        --------------
  */


  // testing that Position 0 0 is equal to another Position 0 0
  @Test
  public void equalsPositionZeroZero() {
    assertTrue(this.zeroZero.equals(new Position2D(0, 0)));
  }

  // testing that Position 0 0 is not equal to Position 1 0
  @Test
  public void notEqualsPositionZeroZeroOneZero() {
    assertFalse(this.zeroZero.equals(new Position2D(1, 0)));
  }


}