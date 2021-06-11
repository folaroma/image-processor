//import static org.junit.Assert.assertEquals;
//
//import cs3500.imageprocessor.model.ImageProcessorModel;
//import cs3500.imageprocessor.model.ImageProcessorModelImpl;
//import cs3500.imageprocessor.controller.fileReading.PPMFileReader;
//import cs3500.imageprocessor.controller.fileWriting.PPMFileWriter;
//import cs3500.imageprocessor.model.filters.FilterBlur;
//import cs3500.imageprocessor.model.images.ImageInterface;
//import java.io.IOException;
//import org.junit.Test;
//
//public class ImageImplTest {
//
//  @Test
//  public void getNeighbors() {
//    ImageProcessorModel model = new ImageProcessorModelImpl("res/Koala.ppm", new PPMFileReader());
//    ImageInterface blurKoala = model.filterImage("res/Koala.ppm", new FilterBlur());
//    model.addImage("blurKoala", blurKoala);
//    try {
//      model.exportImage("res/KoalaBlur.ppm", "blurKoala", new PPMFileWriter());
//    }
//    catch(IOException io) {
//      throw new IllegalStateException();
//    }
//
//  }
//}