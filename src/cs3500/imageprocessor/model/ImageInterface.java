package cs3500.imageprocessor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface to represent an image, constructed of pixels.
 */
public interface ImageInterface {

  List<ArrayList<IPixel>> getPixels();

  void filter(IPixel pixel, double[][] matrix);


}
