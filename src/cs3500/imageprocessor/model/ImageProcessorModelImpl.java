package cs3500.imageprocessor.model;

import java.util.List;

/**
 * Class representing a simple image processing program model. Images are represented using the
 * ImageInterface implementation, and images can either be created programmatically or read in
 * through PPM format.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {
  private ImageInterface image;

  ImageProcessorModelImpl(ImageInterface image) {
    this.image = image;
  }

  ImageProcessorModelImpl(String filename) {
    this.image = ImageUtil.readPPM(filename);
  }

  ImageProcessorModelImpl(int size, int numPiles, List<IColor> colors) {
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
}
