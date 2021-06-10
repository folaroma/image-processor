import static org.junit.Assert.*;

import cs3500.imageprocessor.model.filters.AbstractFilter;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractFilterTest {

  private IFilter filters;

  protected abstract IFilter filters();

  @Before
  public void init() {
    this.filters = filters();
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

  // testing abstract filter positive kernel exception
  @Test(expected = IllegalArgumentException.class)
  public void abstractFilterPositiveEx() {
    AbstractFilter posKernelFilter = new BadFilter(new double[][]{{1, 2},{2, 3}});
  }

  // testing abstract filter non square exception
  @Test(expected = IllegalArgumentException.class)
  public void abstractFilterNonSquareEx() {
    AbstractFilter nonSquareFilter = new BadFilter(new double[][]{{1, 2, 3},{2, 3, 4}});
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


}