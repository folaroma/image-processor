import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;

public class ImageProcessorMain {

  public static void main(String[] args) throws IOException {
    ImageProcessorModel testModel = new ImageProcessorModelImpl("res/Koala.ppm");
    ImageInterface sharpKoala =  testModel.filterSharpen("res/Koala.ppm");
    testModel.addImage("sharpKoala", sharpKoala);
    testModel.exportImage("res/KoalaSharpen.ppm", "sharpKoala");
    ImageInterface sharperKoala =  testModel.filterSharpen("sharpKoala");
    testModel.addImage("sharperKoala", sharperKoala);
    testModel.exportImage("res/KoalaDoubleSharpen.ppm", "sharperKoala");
  }
}
