package cs3500.imageprocessor.controller.fileWriting;

import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class to write PPM ASCII image files from ImageInterface data.
 */
public class PPMFileWriter implements IImageFileWriter {

  @Override
  public void writeFile(String filename, ImageInterface image) throws IOException {
    if (filename == null || image == null) {
      throw new IllegalArgumentException("Argument is null.");
    }
    String ppmString = this.generatePPM(image);
    FileOutputStream file = new FileOutputStream(filename);
    file.write(ppmString.getBytes());
    file.close();
  }

  /**
   * Generates the string in the format of a PPM ASCII image for the given image data.
   * @param image Image to generate the string from.
   * @return The generated PPM ASCII string.
   */
  private String generatePPM(ImageInterface image) {
    StringBuilder ppmString = new StringBuilder().append("P3\n")
        .append(image.getPixels().get(0).size()).append(" ")
        .append(image.getPixels().size()).append("\n255\n");
    for (int i = 0; i < image.getPixels().size(); i++) {
      for (int j = 0; j < image.getPixels().get(0).size(); j++) {
        IPixel currentPixel = image.getPixels().get(i).get(j);
        ppmString.append(currentPixel.getColor().getRed()).append(" ")
            .append(currentPixel.getColor().getGreen()).append(" ")
            .append(currentPixel.getColor().getBlue()).append(" ");
      }
      ppmString.append("\n");
    }
    return ppmString.toString();
  }
}
