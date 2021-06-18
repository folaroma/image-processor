import cs3500.imageprocessor.ImageProcessorControllerImpl;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.controller.filewriting.PNGImageIOWriter;
import cs3500.imageprocessor.controller.filewriting.PPMFileWriter;
import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class to work with the model of the image processor.
 */
public class ImageProcessorMain {

  /**
   * Main method for the model.
   */
  public static void main(String[] args) throws IOException {

    new ImageProcessorControllerImpl(new ImageProcessorModelImpl(),
        new InputStreamReader(System.in), System.out).startEditor();

    /*
    ImageProcessorModel testModel = new ImageProcessorModelImpl();

    testModel.addImage("desert", new PPMFileReader().readImageFromFile("res/desert.ppm"));

    ImageInterface sharpDesert = testModel.sharpen("desert");
    testModel.addImage("sharpDesert", sharpDesert);
    new PNGImageIOWriter().writeFile("res/sharpDesert.png", testModel.getImage("sharpDesert"));

    ImageInterface blurDesert = testModel.blur("desert");
    testModel.addImage("blurDesert", blurDesert);
    new PPMFileWriter().writeFile("res\\blurDesert.ppm", testModel.getImage("blurDesert"));

    ImageInterface monochromeDesert = testModel
        .grayscale("desert");
    testModel.addImage("monochromeDesert", monochromeDesert);
    new PPMFileWriter()
        .writeFile("res\\grayscaleDesert.ppm", testModel.getImage("monochromeDesert"));

    ImageInterface sepiaDesert = testModel
        .sepia("desert");
    testModel.addImage("sepiaDesert", sepiaDesert);
    new PPMFileWriter().writeFile("res\\sepiaDesert.ppm", testModel.getImage("sepiaDesert"));

    testModel.addImage("bug", new PPMFileReader().readImageFromFile("res\\bug.ppm"));

    ImageInterface sharpBug = testModel.sharpen("bug");
    testModel.addImage("sharpBug", sharpBug);
    new PPMFileWriter().writeFile("res\\sharpBug.ppm", testModel.getImage("sharpBug"));

    ImageInterface blurBug = testModel.blur("bug");
    testModel.addImage("blurBug", blurBug);
    new PPMFileWriter().writeFile("res\\blurBug.ppm", testModel.getImage("blurBug"));

    ImageInterface monochromeBug = testModel.grayscale("bug");
    testModel.addImage("monochromeBug", monochromeBug);
    new PPMFileWriter()
        .writeFile("res\\monochromeBug.ppm", testModel.getImage("monochromeBug"));

    ImageInterface sepiaBug = testModel
        .sepia("bug");
    testModel.addImage("sepiaBug", sepiaBug);
    new PPMFileWriter().writeFile("res\\sepiaBug.ppm", testModel.getImage("sepiaBug"));

    ImageProcessorModel checkerboardModel = new ImageProcessorModelImpl();
    checkerboardModel.addImage("checkerboard", new ImageIOFileReader().readImageFromFile("res/checkerboard.png"));
    new PPMFileWriter().writeFile("res/checkerboardPPM.ppm", checkerboardModel.getImage("checkerboard"));
     */

  }

}
