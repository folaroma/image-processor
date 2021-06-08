import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import org.junit.Test;

public class ImageImplTest {

  @Test
  public void getNeighbors() {
    ImageProcessorModel model = new ImageProcessorModelImpl("res/Koala.ppm");
    ImageInterface blurKoala = model.filterBlur(model.getImage("res/Koala.ppm"));
    model.addImage("blurKoala", blurKoala);
    try {
      model.exportImage("res/KoalaBlur.ppm", "blurKoala");
    }
    catch(IOException io) {
      throw new IllegalStateException();
    }

  }
}