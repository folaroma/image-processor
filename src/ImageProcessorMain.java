import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import java.io.IOException;

public class ImageProcessorMain {

  public static void main(String[] args) throws IOException {
    ImageProcessorModel testModel = new ImageProcessorModelImpl("res/Koala.ppm");
    testModel.filterSharpen();
    testModel.exportImage("res/KoalaSharpen.ppm");
  }
}
