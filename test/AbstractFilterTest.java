import static org.junit.Assert.*;

import cs3500.imageprocessor.model.filters.AbstractFilter;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import testingfilters.BadFilter;

public abstract class AbstractFilterTest {

  private IFilter filters;
  private ImageInterface greenRedCheckerBoard;

  protected abstract IFilter filters();

  @Before
  public void init() {
    this.filters = filters();
    greenRedCheckerBoard = new CheckerboardGenerator(2, 2,
        new ArrayList<>(Arrays.asList(new ColorImpl(255, 0, 0),
            new ColorImpl(0, 255, 0)))).generateImage();
  }

  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */


  // testing abstract filter null exception
  @Test(expected = IllegalArgumentException.class)
  public void abstractFilterNullEx() {
    AbstractFilter nullFilter = new BadFilter(null);
  }

  // testing abstract filter even kernel exception
  @Test(expected = IllegalArgumentException.class)
  public void abstractFilterEvenEx() {
    AbstractFilter posKernelFilter = new BadFilter(new double[][]{{1, 2}, {2, 3}});
  }

  // testing abstract filter non square exception
  @Test(expected = IllegalArgumentException.class)
  public void abstractFilterNonSquareEx() {
    AbstractFilter nonSquareFilter = new BadFilter(new double[][]{{1, 2, 3}, {2, 3}, {2, 2}});
  }

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | applyFilter Exception Tests |
        -----------------------------
  */


  @Test(expected = IllegalArgumentException.class)
  public void nullImageApplyFilterEx() {
    this.filters.applyFilter(null);
  }


  // tests for clamping
  // tests that color values are clamped to 255 when they exceed 255.
  @Test
  public void testClampingOverLimit() {
    ImageInterface overLimitBoard = new BadFilter(new double[][]{{4, 4, 4}, {4, 4, 4}, {4, 4, 4}})
        .applyFilter(greenRedCheckerBoard);
    assertEquals(255, overLimitBoard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(255, overLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getBlue());
  }


  // tests that color values are clamped to 0 when they are below 0.
  @Test
  public void testClampingUnderLimit() {
    ImageInterface overLimitBoard = new BadFilter(new double[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}})
        .applyFilter(greenRedCheckerBoard);
    assertEquals(0, overLimitBoard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, overLimitBoard.getPixels().get(0).get(1).getColor().getBlue());
  }

}