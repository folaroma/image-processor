import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.colorTransformations.GrayscaleTransformation;
import cs3500.imageprocessor.model.colorTransformations.SepiaTransformation;
import cs3500.imageprocessor.controller.fileReading.PPMFileReader;
import cs3500.imageprocessor.controller.fileWriting.PPMFileWriter;
import cs3500.imageprocessor.model.filters.FilterSharpen;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageProcessorMain {

  public static void main(String[] args) throws IOException {
    ImageProcessorModel testModel = new ImageProcessorModelImpl();
    testModel.addImage("res/Koala.ppm", new PPMFileReader().readImageFromFile("res/Koala.ppm"));

    ImageInterface sharpKoala = testModel.filterImage("res/Koala.ppm", new FilterSharpen());
    testModel.addImage("sharpKoala", sharpKoala);
    new PPMFileWriter().writeFile("res/KoalaSharpen.ppm", testModel.getImage("sharpKoala"));

    ImageInterface sharperKoala = testModel.filterImage("sharpKoala", new FilterSharpen());
    testModel.addImage("sharperKoala", sharperKoala);
    new PPMFileWriter().writeFile("res/KoalaDoubleSharpen.ppm", testModel.getImage("sharperKoala"));

    ImageInterface checkerboard = new CheckerboardGenerator(100, 100,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))).generateImage();
    testModel.addImage("checkerboard", checkerboard);
    new PPMFileWriter().writeFile("res/Checkerboard.ppm", testModel.getImage("checkerboard"));


    ImageInterface monochromeKoala = testModel
        .transformImage("res/Koala.ppm", new GrayscaleTransformation());
    testModel.addImage("monochromeKoala", monochromeKoala);
    new PPMFileWriter().writeFile("res\\monochromeKoala.ppm", testModel.getImage("monochromeKoala"));


    ImageInterface sepiaKoala = testModel
        .transformImage("res/Koala.ppm", new SepiaTransformation());
    testModel.addImage("sepiaKoala", sepiaKoala);
    new PPMFileWriter().writeFile("res/sepiaKoala.ppm", testModel.getImage("sepiaKoala"));



  }

}
