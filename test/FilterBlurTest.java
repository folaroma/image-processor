import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.filters.FilterBlur;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class FilterBlurTest extends AbstractFilterTest {

  protected IFilter filters() {
    return new FilterBlur();
  }

  // testing general blurring
  @Test
  public void applyBlur() {
    ImageInterface checkerboard = new CheckerboardGenerator(100, 100,
        new ArrayList<>(Arrays.asList(new ColorImpl(100, 100, 100),
            new ColorImpl(200, 200, 200)))).generateImage();

    assertEquals(
        filters().applyFilter(checkerboard).getPixels().get(0).get(1).getColor().getRed(), 110);

  }

  // testing general blurring, twice
  @Test
  public void applyBlurTwice() {
    ImageInterface checkerboard = new CheckerboardGenerator(100, 100,
        new ArrayList<>(Arrays.asList(new ColorImpl(100, 100, 100),
            new ColorImpl(200, 200, 200)))).generateImage();

    ImageInterface checkerBoardBlur = filters().applyFilter(checkerboard);

    assertEquals(filters().applyFilter(checkerBoardBlur)
        .getPixels().get(0).get(1).getColor().getRed(), 84);

  }


}