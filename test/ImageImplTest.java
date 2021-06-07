import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.ImageInterface;
import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import java.io.IOException;
import org.junit.Test;

public class ImageImplTest {

  @Test
  public void getNeighbors() {
    ImageProcessorModel model = new ImageProcessorModelImpl("res/Koala.ppm");
    model.filterBlur();
    try {
      model.exportImage("res/KoalaBlur.ppm");
    }
    catch(IOException io) {
      throw new IllegalStateException();
    }

  }
}