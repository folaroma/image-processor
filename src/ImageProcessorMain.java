import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.colorTransformations.GrayscaleTransformation;
import cs3500.imageprocessor.model.colorTransformations.SepiaTransformation;
import cs3500.imageprocessor.model.fileReading.PPMFileReader;
import cs3500.imageprocessor.model.fileWriting.PPMFileWriter;
import cs3500.imageprocessor.model.filters.FilterSharpen;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageProcessorMain {

  public static void main(String[] args) throws IOException {
    ImageProcessorModel testModel = new ImageProcessorModelImpl("res/Koala.ppm",
        new PPMFileReader());

    ImageInterface sharpKoala = testModel.filterImage("res/Koala.ppm", new FilterSharpen());
    testModel.addImage("sharpKoala", sharpKoala);
    testModel.exportImage("res/KoalaSharpen.ppm", "sharpKoala", new PPMFileWriter());

    ImageInterface sharperKoala = testModel.filterImage("sharpKoala", new FilterSharpen());
    testModel.addImage("sharperKoala", sharperKoala);
    testModel.exportImage("res/KoalaDoubleSharpen.ppm", "sharperKoala", new PPMFileWriter());

    ImageInterface checkerboard = new CheckerboardGenerator(100, 100,
        new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))).generateImage();
    testModel.addImage("checkerboard", checkerboard);
    testModel.exportImage("res/Checkerboard.ppm", "checkerboard", new PPMFileWriter());

    ImageInterface monochromeKoala = testModel
        .transformImage("res/Koala.ppm", new GrayscaleTransformation());
    testModel.addImage("monochromeKoala", monochromeKoala);
    testModel.exportImage("res/monochromeKoala.ppm", "monochromeKoala", new PPMFileWriter());

    ImageInterface sepiaKoala = testModel
        .transformImage("res/Koala.ppm", new SepiaTransformation());
    testModel.addImage("sepiaKoala", sepiaKoala);
    testModel.exportImage("res/sepiaKoala.ppm", "sepiaKoala", new PPMFileWriter());


  }

}
