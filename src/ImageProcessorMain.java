import cs3500.imageprocessor.controller.ImageProcessorControllerImpl;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
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
