import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.ImageUtil;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;

public class ImageProcessorMain {

  public static void main(String[] args) throws IOException {
    ImageProcessorModel testModel = new ImageProcessorModelImpl("res/Koala.ppm");

    ImageInterface sharpKoala = testModel.filterSharpen(testModel.getImage("res/Koala.ppm"));
    testModel.addImage("sharpKoala", sharpKoala);
    testModel.exportImage("res/KoalaSharpen.ppm", "sharpKoala");

    ImageInterface sharperKoala = testModel.filterSharpen(testModel.getImage("sharpKoala"));
    testModel.addImage("sharperKoala", sharperKoala);
    testModel.exportImage("res/KoalaDoubleSharpen.ppm", "sharperKoala");

    testModel.addImage("res\\amitdrip.ppm", ImageUtil.readPPM("res\\amitdrip.ppm"));
    ImageInterface sharpDrip = testModel.filterSharpen(
        testModel.filterSharpen(testModel.filterSharpen(testModel.getImage("res\\amitdrip.ppm"))));
    testModel.addImage("sharpDrip", sharpDrip);
    testModel.exportImage("res\\sharpamitdrip.ppm", "sharpDrip");
  }
}
