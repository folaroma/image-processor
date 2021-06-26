import cs3500.imageprocessor.controller.ImageProcessorController;
import cs3500.imageprocessor.controller.ImageProcessorControllerImpl;
import cs3500.imageprocessor.controller.ImageProcessorGUIController;
import cs3500.imageprocessor.model.MultiLayerProcessorModel;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class to work with the image processing program/
 */
public class ImageProcessorMain {

  /**
   * Main method for the program.
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 2) {
      if (args[0].equals("-script")) {
        new ImageProcessorControllerImpl(new MultiLayerProcessorModelImpl(),
            new FileReader(args[1]), System.out).startEditor();
      }
    } else if (args.length == 1 && args[0].equals("-text")) {
      new ImageProcessorControllerImpl(new MultiLayerProcessorModelImpl(),
          new InputStreamReader(System.in), System.out).startEditor();
    } else if (args.length == 1 && args[0].equals("-interactive")) {
      MultiLayerProcessorModel testModel = new MultiLayerProcessorModelImpl();
      ImageProcessorController controller = new ImageProcessorGUIController(testModel);
      controller.startEditor();
    } else {
      System.out.println("Bad arguments for program.");
    }
  }
}

