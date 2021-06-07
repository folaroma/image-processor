import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.ImageInterface;
import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import org.junit.Test;

public class ImageImplTest {

  @Test
  public void getNeighbors() {
    ImageProcessorModel model = new ImageProcessorModelImpl("res/Koala.ppm");
    ImageInterface modelImage = model.getImage();
    double[][] blur = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
    System.out.println(blur.length);

    modelImage.filter(modelImage.getPixels().get(0).get(1), blur);

  }
}