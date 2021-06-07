package cs3500.imageprocessor.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Class representing a simple image processing program model. Images are represented using the
 * ImageInterface implementation, and images can either be created programmatically or read in
 * through PPM format.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {

  private ImageInterface image;

  public ImageProcessorModelImpl(ImageInterface image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.image = image;
  }

  public ImageProcessorModelImpl(String filename) {
    this.image = ImageUtil.readPPM(filename);
  }

  public ImageProcessorModelImpl(int size, int numPiles, List<IColor> colors) {
    this.image = this.createCheckerboard(size, numPiles, colors);
  }

  @Override
  public void filterBlur() {

  }

  @Override
  public void filterSharpen() {

  }

  @Override
  public void colorMonochrome() {

  }

  @Override
  public void colorSepia() {

  }

  @Override
  public ImageInterface createCheckerboard(int size, int numTiles, List<IColor> colors) {
    return null;
  }

  @Override
  public void exportImage(String filename) throws IOException {
    String ppmString = this.generatePPM();
    FileOutputStream file = new FileOutputStream(filename);
    file.write(ppmString.getBytes());
    file.close();
  }

  private String generatePPM() {
    StringBuilder ppmString = new StringBuilder().append("P3\n")
        .append(this.image.getPixels().get(0).size()).append(" ")
        .append(this.image.getPixels().size()).append("\n255\n");
    for (int i = 0; i < this.image.getPixels().size(); i++) {
      for (int j = 0; j < this.image.getPixels().get(0).size(); j++) {
        IPixel currentPixel = this.image.getPixels().get(i).get(j);
        ppmString.append(currentPixel.getColor().getRed()).append(" ")
            .append(currentPixel.getColor().getGreen()).append(" ")
            .append(currentPixel.getColor().getBlue()).append(" ");
      }
      ppmString.append("\n");
    }
    return ppmString.toString();
  }
}