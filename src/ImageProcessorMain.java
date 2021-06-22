import cs3500.imageprocessor.controller.ImageProcessorController;
import cs3500.imageprocessor.controller.ImageProcessorGUIController;
import cs3500.imageprocessor.model.MultiLayerProcessorModel;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.view.ImageProcessorGUIViewImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 * Main class to work with the model of the image processor.
 */
public class ImageProcessorMain {

  /**
   * Main method for the model.
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
//    if (args.length == 2) {
//      if (args[0].equals("script")) {
//        new ImageProcessorControllerImpl(new MultiLayerProcessorModelImpl(),
//            new FileReader(args[1]), System.out).startEditor();
//      }
//    } else if (args.length == 1 && args[0].equals("interactive")) {
//      new ImageProcessorControllerImpl(new MultiLayerProcessorModelImpl(),
//          new InputStreamReader(System.in), System.out).startEditor();
//    } else {
//      throw new IllegalArgumentException("Bad arguments for program.");
//    }
//  }
    MultiLayerProcessorModel testModel = new MultiLayerProcessorModelImpl();
    testModel.addImage("checkerboard", testModel.generateCheckerboard(100, 100, new ArrayList<>(
        Arrays.asList(new ColorImpl(255, 0,0), new ColorImpl(0, 0, 0)))));
    ImageProcessorController controller = new ImageProcessorGUIController(testModel);
    controller.startEditor();
  }
}
