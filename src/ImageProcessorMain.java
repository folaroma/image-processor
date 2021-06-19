import cs3500.imageprocessor.ImageProcessorControllerImpl;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.controller.filewriting.PNGImageIOWriter;
import cs3500.imageprocessor.controller.filewriting.PPMFileWriter;
import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class to work with the model of the image processor.
 */
public class ImageProcessorMain {

  /**
   * Main method for the model.
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
    if (args.length == 2) {
      if (args[0].equals("script")) {
        new ImageProcessorControllerImpl(new MultiLayerProcessorModelImpl(),
            new FileReader(args[1]), System.out).startEditor();
      }
    }
    if (args.length == 1 && args[0].equals("interactive")) {
      new ImageProcessorControllerImpl(new MultiLayerProcessorModelImpl(),
          new InputStreamReader(System.in), System.out).startEditor();
    }
    else {
      throw new IllegalArgumentException("Bad arguments for program.");
    }
  }
}
