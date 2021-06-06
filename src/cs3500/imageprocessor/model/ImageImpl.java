package cs3500.imageprocessor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a simple implementation of an Image, which is a 2D array of pixels. This array
 * is represneted by a 2D ArrayList.
 */
public class ImageImpl implements ImageInterface {

  private final List<ArrayList<IPixel>> pixels;

  public ImageImpl(List<ArrayList<IPixel>> pixels) {
    this.pixels = pixels;
  }

  @Override
  public List<ArrayList<IPixel>> getPixels() {
    return null;
  }
}
