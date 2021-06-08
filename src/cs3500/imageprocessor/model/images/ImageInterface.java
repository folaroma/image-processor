package cs3500.imageprocessor.model.images;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface to represent an image, constructed of pixels.
 */
public interface ImageInterface {

  List<ArrayList<IPixel>> getPixels();

  IPixel filter(IPixel pixel, double[][] matrix);

  IPixel colorTransform(IPixel pixel, double[][] matrix);


}
